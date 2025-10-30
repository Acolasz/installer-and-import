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
kubectl logs pods/<pod_name> <container_name> -n <namespace>

kubectl logs pods/service-pods service-1
kubectl logs pods/service-pods service-1 -n <namespace>
```

## Pod exec

```shell
kubectl exec -it pods/<pod_name> -- bash
kubectl exec -it pods/<pod_name> -n <namespace> -- bash

kubectl exec -it pods/service-pods --container service-1 -- bash
kubectl exec -it pods/service-pods --container service-1 -n <namespace> -- bash
```

## Pod cp (copy to local or to container)

```shell
# to local
kubectl cp pod/<pod_name>:<container_path> ./ --container <container_name> <local_path> -n <namespace>
# to container
kubectl cp pod/<pod_name>:<container_path> --container <container_name> <local_path> -n <namespace>
kubectl cp ./FreeSerif.ttf pod/eets-document-service-6c8464cd4-nx2gr:/fonts/roboto/ --container eets-document-service -n dev-eets
kubectl cp ./FreeSerif.ttf dev-eets/eets-document-service-6c8464cd4-nx2gr:/home/icellmobilsoft/fonts/roboto --container eets-document-service -n dev-eets
kubectl cp ~/workspace/gitlab/hugo/devops/hugo-build/modules/eets/eets-document-service/configs/fonts/FreeSerif.ttf dev-eets/eets-document-service-79747b8bfc-hlgpz:/home/icellmobilsoft/config --container eets-document-service

kubectl cp dev-eets/eets-document-service-6c8464cd4-nx2gr:/home/icellmobilsoft/config/cli.properties ./cli.properties --container eets-document-service

kubectl cp ./grafana.db pod/prometheus-grafana-6db8b7d854-fvhq9:/var/lib/grafana/grafana.db -c grafana -n monitoring
```

## Pod delete force

```shell
kubectl delete pod/
```
