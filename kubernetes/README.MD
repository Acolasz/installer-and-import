# Kubernetes

# Objects

# Commands

## Run (pod)

```shell
kubectl run ubuntu-tst --image ubuntu:22.04
kubectl run ubuntu-tst -it --image ubuntu -- bash
```

## Events

[Usage Events][events]

## Pods

[Usage Pods][pods]

## Secrets

[Usage Secrets][secrets]

## Annotate

[Usage Annotate][annotate]

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
k config set-cluster k8z --server https://külső-IP-cím --insecure-skip-tls-verify
k config set-credentials k8z --token=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token )
k config set-context k8z --cluster k8z --user k8z --namespace $NS
k config get-contexts
k config view
k config view --minify
k config view --minify --flatten
```

## RBAC

```shell
k create rolebinding list --clusterrole view --group system:serviceaccounts:<namespace>
k create role <role_name> --resource svc,deployments --verb list,get
#k create role svclister --resource svc --verb list
k create rolebinding <rolebindig_name> --role view --group system:serviceaccounts:<namespace>
```

# Operators and Kubernetes tools

## Docker desktop

## kubernetes dashboard

install [Kubernetes dashboard][kube_dashboard] on browser

# Related article

* helm charts [helm][helm]
* **Advanced**: Workflow in kubernetes - [What happens when you create a Pod in Kubernetes?][create_pod_in_kubernetes]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[events]:<./EVENTS.MD>

[pods]:<./PODS.MD>

[secrets]:<./SECRETS.MD>

[annotate]:<./ANNOTATE.MD>

[kube_dashboard]:<./kubernetes/docker-desktop/README.MD>

[create_pod_in_kubernetes]:<https://itnext.io/what-happens-when-you-create-a-pod-in-kubernetes-6b789b6db8a8>
