# Kubernetes events

## list

```shell
kubeclt get events
```

### Advanced

```shell
kubectl get events \
--all-namespaces \
--field-selector involvedObject.kind=Deployment,involvedObject.name=<deployment_name> \
-A \
--sort-by='.lastTimestamp'
#
kubectl get events \
--all-namespaces \
--field-selector involvedObject.kind=Deployment,involvedObject.name=<deployment_name> \
-A \
--sort-by='.metadata.creationTimestamp'
```

# Back to the [README.MD][readme]

[readme]:<./README.MD>
