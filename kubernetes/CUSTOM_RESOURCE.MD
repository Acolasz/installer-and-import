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
kubectl get challenge.acme.cert-manager.io -n cert-manager
```

### Describe

```shell
kubectl describe crd certificaterequests
```

### Explain

```shell
kubectl explain certificaterequests.spec
kubectl explain certificaterequests.spec.issuerRef
```

### Delete

```shell
kubectl delete crd opensearchactiongroups.opensearch.opster.io
```
## Custom resource

### Delete

```shell
kubectl delete -n cert-manager challenge.acme.cert-manager.io hugo-test-hu-certificate-bm2bq-2428040390-3864750435
kubectl delete -n cert-manager challenge.acme.cert-manager.io --all
kubectl patch challenge.acme.cert-manager.io hugo-test-hu-certificate-bm2bq-2428040390-3864750435 -p '{"metadata":{"finalizers":[]}}' --type=merge -n cert-manager 
kubectl patch certificaterequests/cert-manager.io -p '{"metadata":{"finalizers":[]}}' --type=merge
kubectl patch challenges/acme.cert-manager.io -p '{"metadata":{"finalizers":[]}}' --type=merge
kubectl patch ns longhorn-system -p '{"metadata":{"finalizers":null}}'
```

## Custom resource

### Get/Describe Custom Resource

```shell
kubectl get certificaterequests --all-namespaces
kubectl describe certificaterequests --all-namespaces
```
