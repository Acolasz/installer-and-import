# Usage

## Label operations

```shell
# Add new annotate
kubectl label <resource>/<resource_name> <label_key>:<annotate_value> -n ${NAMESPACE}
# Overwrite annotate
kubectl label --overwrite <resource>/<resource_name> <label_key>:<annotate_value_other> -n ${NAMESPACE}
# Remove new annotate
kubectl label <resource>/<resource_name> <label_key>- -n ${NAMESPACE}
```

## Annotate operations

```shell
# Add new annotate
kubectl annotate <resource>/<resource_name> <annotate_key>:<annotate_value> -n ${NAMESPACE}
# Overwrite annotate
kubectl annotate --overwrite <resource>/<resource_name> <annotate_key>:<annotate_value_other> -n ${NAMESPACE}
# Remove new annotate
kubectl annotate <resource>/<resource_name> <annotate_key>- -n ${NAMESPACE}
```
