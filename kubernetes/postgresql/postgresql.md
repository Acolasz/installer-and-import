#

```shell
helm repo add cetic https://cetic.github.io/helm-charts

helm repo update

helm search repo postgresql

helm upgrade --install --create-namespace postgresql cetic/postgresql --version 0.2.4 -n awx
```


