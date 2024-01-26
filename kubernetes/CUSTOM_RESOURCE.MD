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
kubectl describe crd certificaterequests.cert-manager.io
```

## Custom resource
