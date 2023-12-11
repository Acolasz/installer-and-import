# Helm charts

* reflector [emberstack/kubernetes-reflector][reflector]

# Usage

## reflector

```shell
kubectl annotate secret/service-password reflector.v1.k8s.emberstack.com/reflection-allowed=true -n <SOURCE_NAMESPACE>
kubectl annotate secret/service-password reflector.v1.k8s.emberstack.com/reflection-allowed-namespaces=<TARGET_NAMESPACE_1>,<TARGET_NAMESPACE_2> -n <SOURCE_NAMESPACE>
kubectl annotate secret/service-password reflector.v1.k8s.emberstack.com/reflection-auto-enabled=true -n <SOURCE_NAMESPACE>
# check
kubectl get secrets -n <TARGET_NAMESPACE_1>
kubectl get secrets -n <TARGET_NAMESPACE_2>
```

[reflector]:<https://github.com/emberstack/kubernetes-reflector>