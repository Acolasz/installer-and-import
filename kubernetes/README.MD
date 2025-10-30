# Kubernetes

## kubeconfig

> [kubeconfig][kubeconfig]

## kubectl plugins

> [kubectl plugins][kubectl_plugins]

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

[Usage CUSTOM RESOURCE][custom_resource]

```shell
kubectl api-resources
kubectl explain <resources>.spec | more 
kubectl explain <resources>.kind 

kubectl get endpoints
```

# Operators and Kubernetes tools

## Docker desktop

## kubernetes dashboard

install [Kubernetes dashboard][kube_dashboard] on browser

# Related article

* **Advanced**: Workflow in kubernetes - [What happens when you create a Pod in Kubernetes?][create_pod_in_kubernetes]
* CNCF [Cloud Native Interactive Landscape][cncf]
* [kubedaily.com][kubedaily]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[namespace]:<./NAMESPACE.MD>

[events]:<./EVENTS.MD>

[pods]:<./PODS.MD>

[secrets]:<./SECRETS.MD>

[annotate]:<./ANNOTATE.MD>

[rbac]:<./RBAC.MD>

[custom_resource]:<./CUSTOM_RESOURCE.MD>

[kubeconfig]:<./KUBECONFIG.MD>

[kube_dashboard]:<./kubernetes/docker-desktop/README.MD>

[create_pod_in_kubernetes]:<https://itnext.io/what-happens-when-you-create-a-pod-in-kubernetes-6b789b6db8a8>

[kubectl_plugins]:<./KUBECTL_PLUGINS.MD>

[cncf]:<https://landscape.cncf.io/>

[kubedaily]:<https://kubedaily.com/tools>
