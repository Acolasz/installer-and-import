# Usage

## Pod manifest

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: service-pods
spec:
  initContainers:
    - name: init-1
  containers:
    - name: service-1
      image: >-
        docker.io/account/service-1:1.0.0
```

## Pod status

```shell
kubectl get pods
kubectl describe pod <pod_name>
kubectl describe pods/<pod_name>
```

## Pod logs

```shell
kubectl logs pods/<pod_name> <container_name>

kubectl logs pods/service-pods service-1
```