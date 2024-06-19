# Custom resource

## Custom resource definition

### Get
```shell
kubectl api-resources
```

```shell
kubectl get customresourcedefinitions
kubectl get crd
kubectl api-resources --api-group=cert-manager.io
```

### Describe

```shell
kubectl describe crd certificaterequests
```

### Delete

```shell
kubectl patch certificaterequests/cert-manager.io -p '{"metadata":{"finalizers":[]}}' --type=merge
```

## Custom resource

### Get/Describe Custom Resource

```shell
kubectl get certificaterequests --all-namespaces
kubectl describe certificaterequests --all-namespaces
```
