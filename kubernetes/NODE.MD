# Disk pressure

## Kubelet

> [Viewing the kubelet configuration][kubelet_config]
> ```shell
curl -X GET http://127.0.0.1:8001/api/v1/nodes/<node-name>/proxy/configz | jq .
  ```

# Usage

## Taint

```shell
kubectl taint --help
```

```shell
# Add taint
kubectl taint nodes <node-name> <key>=<value>:NoSchedule
# Update taint
kubectl taint --overwrite nodes <node-name> <key>=<value_other>:NoSchedule
```

## Labal

```shell
# Add new annotate
kubectl label nodes/<node-name> <label_key>=<label_value>
# Overwrite annotate
kubectl label --overwrite nodes/<node-name> <label_key>=<label_value_other>
# Remove new annotate
kubectl label nodes/<node-name> <label_key>-
```

## Annotate

```shell
# Add new annotate
kubectl annotate nodes/<node-name> <annotate_key>=<annotate_value>
# Overwrite annotate
kubectl annotate --overwrite nodes/<node-name> <annotate_key>=<annotate_value_other>
# Remove new annotate
kubectl annotate nodes/<node-name> <annotate_key>-
```

[kubelet_config]:<https://kubernetes.io/docs/tasks/administer-cluster/kubelet-config-file/#viewing-the-kubelet-configuration>
