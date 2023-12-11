#

## Install Operator

```shell
helm repo add awx-operator https://ansible.github.io/awx-operator/

helm repo update

helm search repo awx-operator

helm upgrade --install --create-namespace awx-operator awx-operator/awx-operator --version 2.7.0 -n awx
```

## Install AWX console

```shell
k apply -f awx-demo-postrges-secret.yaml -f awx.yaml
```

### Troubleshooting

```shell
kubectl logs deployments/awx-operator-controller-manager -c awx-manager -f
```

## Uninstall AWX console

```shell
k delete -f awx-demo-postrges-secret.yaml -f awx.yaml
```

# Related article

* [database-configuration.md][database_configuration_md]
* helm [commands][helm_cmd]

[helm_cmd]:<../../commands/helm/helm.md>

[database_configuration_md]:<https://github.com/ansible/awx-operator/blob/devel/docs/user-guide/database-configuration.md>
