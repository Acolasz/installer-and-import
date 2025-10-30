# Scale

```shell
kubectl scale --replicas=0 statefulset/<statefulset_name> -n <namespace>
kubectl scale --replicas=1 statefulset/<statefulset_name> -n <namespace>
kubectl scale --replicas=0 deployment/<deployment_name> -n <namespace>
kubectl scale --replicas=1 deployment/<deployment_name> -n <namespace>
```