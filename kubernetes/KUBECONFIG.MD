# Context

## External kubeconfig

```shell
kubectl config set-cluster k8z --server https://külső-IP-cím --insecure-skip-tls-verify
kubectl config set-credentials k8z --token=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token )
kubectl config set-context k8z --cluster k8z --user k8z --namespace $NS
kubectl config get-contexts
kubectl config view
kubectl config view --minify
kubectl config view --minify --flatten
```

## RBAC

```shell
kubectl create rolebinding list --clusterrole view --group system:serviceaccounts:<namespace>
kubectl create role <role_name> --resource svc,deployments --verb list,get
kubectl create rolebinding <rolebindig_name> --role view --group system:serviceaccounts:<namespace>
```

## Create kubeconfig

> [Kubeconfig File Explained With Practical Examples][create_kubeconfig1]
> [Kubernetes Authentication: Client Certificate][create_kubeconfig2]

```shell
kubectl -n kube-system create serviceaccount devops-cluster-admin

cat << EOF | kubectl apply -f -
apiVersion: v1
kind: Secret
metadata:
  name: devops-cluster-admin-secret
  namespace: kube-system
  annotations:
    kubernetes.io/service-account.name: devops-cluster-admin
type: kubernetes.io/service-account-token
EOF
```

```shell
cat << EOF | kubectl apply -f -
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: devops-cluster-admin
rules:
- apiGroups: [""]
  resources:
  - nodes
  - nodes/proxy
  - services
  - endpoints
  - pods
  verbs: ["get", "list", "watch"]
- apiGroups:
  - extensions
  resources:
  - ingresses
  verbs: ["get", "list", "watch"]
EOF

cat << EOF | kubectl apply -f -
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: devops-cluster-admin
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: devops-cluster-admin
subjects:
- kind: ServiceAccount
  name: devops-cluster-admin
  namespace: kube-system
EOF
```

## Cluster-level

> ClusterRoleBinding
>
>> ServiceAccount
>
>> ClusterRole

## Namespace-level

> RoleBinding
>
>> ServiceAccount
>
>> Role

```shell
export SA_SECRET_TOKEN=$(kubectl -n kube-system get secret/devops-cluster-admin-secret -o=go-template='{{.data.token}}' | base64 --decode)
export CLUSTER_NAME=$(kubectl config current-context)
export CURRENT_CLUSTER=$(kubectl config view --raw -o=go-template='{{range .contexts}}{{if eq .name "'''${CLUSTER_NAME}'''"}}{{ index .context "cluster" }}{{end}}{{end}}')
export CLUSTER_CA_CERT=$(kubectl config view --raw -o=go-template='{{range .clusters}}{{if eq .name "'''${CURRENT_CLUSTER}'''"}}"{{with index .cluster "certificate-authority-data" }}{{.}}{{end}}"{{ end }}{{ end }}')
export CLUSTER_ENDPOINT=$(kubectl config view --raw -o=go-template='{{range .clusters}}{{if eq .name "'''${CURRENT_CLUSTER}'''"}}{{ .cluster.server }}{{end}}{{ end }}')

cat << EOF > ~/.kube/config-icell-hugo-admin
apiVersion: v1
clusters:
- cluster:
    certificate-authority-data: ${CLUSTER_CA_CERT}
    server: ${CLUSTER_ENDPOINT}
  name: ${CLUSTER_NAME}
contexts:
- context:
    cluster: ${CLUSTER_NAME}
    user: devops-cluster-admin
  name: ${CLUSTER_NAME}
current-context: ${CLUSTER_NAME}
kind: Config
preferences: {}
users:
- name: devops-cluster-admin
  user:
    token: ${SA_SECRET_TOKEN}
EOF
```

[create_kubeconfig1]:<https://devopscube.com/kubernetes-kubeconfig-file/>

[create_kubeconfig2]:<https://insujang.github.io/2019-12-18/kubernetes-authentication/>
