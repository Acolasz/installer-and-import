# Plugins list

* [krew][kubectl_plugin_krew]

## krew plugins

* resource-capacity
    * `kubectl krew install resource-capacity`
        * `kubectl resource-capacity`
        * `kubectl resource-capacity --available`
* cnpg
    * `kubectl krew install cnpg`
        * `kubectl cnpg logs cluster $NAME -f -n $NAMESPACE --context $CONTEXT`
* blame
    * `kubectl krew install blame`
    * Annotate each line in the given resource's YAML with information from the managedFields to show who last modified
      the field.
    * Source: [knight42/kubectl-blame][kubectl_plugin_blame]
        * `kubectl-blame $RESOURCE_NAME $NAME -n $NAMESPACE --context $CONTEXT | less -K`
* modify-secret
    * `kubectl krew install modify-secret`
        * `kubectl modify-secret --namespace $NAMESPACE $NAME`

## krew plugin list

```shell
kubectl krew search
```

[kubectl_plugin_krew]:<https://krew.sigs.k8s.io/docs/user-guide/setup/install/>

[kubectl_plugin_cnpg]:<https://github.com/cloudnative-pg/cloudnative-pg/blob/main/docs/src/kubectl-plugin.md>

[kubectl_plugin_blame]:<https://github.com/knight42/kubectl-blame>
