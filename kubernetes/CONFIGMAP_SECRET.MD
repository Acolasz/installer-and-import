# ConfigMap

## Create/Delete

```shell
kubectl create configmap my-config \
--from-file=path/to/bar

kubectl create configmap my-config \
--from-file=ssh-privatekey=~/.ssh/id_rsa \
--from-file=ssh-publickey=~/.ssh/id_rsa.pub

kubectl create configmap my-config \
--from-literal=key1=config1 \
--from-literal=key2=config2
```

### dry-run

```shell
kubectl create configmap my-config --from-file=path/to/bar --dry-run -o yaml > my-config-cm.yaml
kubectl create configmap artemis-broker --from-file=./broker.xml --dry-run -o yaml > broker-cm.yaml
kubectl create configmap jolokia-access-cm --from-file=./jolokia-access.xml --dry-run -o yaml > jolokia-access-cm.yaml

```

# Secret

## Create/Delete

```shell
kubectl create secret generic my-secret \
--from-file=path/to/bar

kubectl create secret generic my-secret \
--from-file=ssh-privatekey=~/.ssh/id_rsa \
--from-file=ssh-publickey=~/.ssh/id_rsa.pub

kubectl create secret generic my-secret \
--from-literal=key1=supersecret \
--from-literal=key2=topsecret
```

### with type

```shell
kubectl create secret generic my-secret \
--type=kubernetes.io/basic-auth \
--from-literal=key1=supersecret \
--from-literal=key2=topsecret
```

## Get data

```shell
# simple data name
kubectl get secrets/db-user-pass --template='{{.data.password | base64decode}}'
# if data key contains "-"
kubectl get secrets/prometheus-grafana --template='{{index .data "admin-password"}}' -n monitoring | base64 -D

# all list
kubectl get secrets/db-user-pass --template='{{ range $key, $value := .data }}{{ printf "%s: %s\n" $key ($value | base64decode) }}{{ end }}'
```

# Related article:

* [kubectl create configMap][kubectl_create_configmap]
* [kubernetes secrets types][kubectl_secrets]
* [K8S ConfigMap by Helm limitation][kubectl_helm_setup]
* [Using Helm To Include All Files From A Directory In-line][kubectl_helm_include_all_files]

[kubectl_create_configmap]:<https://jamesdefabia.github.io/docs/user-guide/kubectl/kubectl_create_configmap/>

[kubectl_secrets]:<https://kubernetes.io/docs/concepts/configuration/secret/>

[kubectl_helm_setup]:<https://www.linkedin.com/pulse/k8s-configmap-helm-limitation-sarris-overbosch/>

[kubectl_helm_include_all_files]:<https://tratnayake.dev/helm-include-all-files-from-directory-in-line>
