# Kubernetes

# Objects

# Commands

## Run (pod)

```shell
kubectl run ubuntu-tst --image ubuntu:22.04
kubectl run ubuntu-tst -it --image ubuntu -- bash
kubectl run tmp-shell --rm -i --tty --image nicolaka/netshootls -- bash
```

## Namespace

[Usage Namespace][namespace]

## Events

[Usage Events][events]

## Pods

[Usage Pods][pods]

## Secrets

[Usage Secrets][secrets]

## Annotate

[Usage Annotate][annotate]

## RBAC

[Usage RBAC][rbac]

# Resources

```shell
kubectl api-resources
kubectl explain <resources>.spec | more 
kubectl explain <resources>.kind 

kubectl get endpoints
```

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
#kubectl create role svclister --resource svc --verb list
kubectl create rolebinding <rolebindig_name> --role view --group system:serviceaccounts:<namespace>
```

# kubectl plugins

> [kubectl plugins][kubectl_plugins]

# Operators and Kubernetes tools

## Docker desktop

## kubernetes dashboard

install [Kubernetes dashboard][kube_dashboard] on browser

# Related article

* helm charts [helm][helm]
* **Advanced**: Workflow in kubernetes - [What happens when you create a Pod in Kubernetes?][create_pod_in_kubernetes]
* CNCF [Cloud Native Interactive Landscape][cncf]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[namespace]:<./NAMESPACE.MD>

[events]:<./EVENTS.MD>

[pods]:<./PODS.MD>

[secrets]:<./SECRETS.MD>

[annotate]:<./ANNOTATE.MD>

[rbac]:<./RBAC.MD>

[kube_dashboard]:<./kubernetes/docker-desktop/README.MD>

[create_pod_in_kubernetes]:<https://itnext.io/what-happens-when-you-create-a-pod-in-kubernetes-6b789b6db8a8>

[kubectl_plugins]:<./KUBECTL_PLUGINS.MD>

[cncf]:<https://landscape.cncf.io/>
