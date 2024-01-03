# Helm charts

* reflector [emberstack/kubernetes-reflector][reflector]
* kube prometheus stack [prometheus-community/kube-prometheus-stack][kube_prometheus_stack]
    * prometheus-operator
    * prometheus-service
    * allertmanager-service
    * grafana
* elastic-operator [elastic/elasticsearch][elastic_operator]
* cert-manager [jetstack/cert-manager][cert_manager]
    * describe [install][cert_manager_install]

# Install

## reflector

```shell
helm repo add emberstack https://emberstack.github.io/helm-charts
helm repo update
helm search repo emberstack/reflector
helm show values emberstack/reflector

NS=reflector
helm upgrade --install \
--create-namespace \
--namespace ${NS} \
reflector emberstack/reflector \
--version 7.1.216
```

## kube prometheus stack

```shell
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm search repo prometheus-community/kube-prometheus-stack
helm show values prometheus-community/kube-prometheus-stack

NS=monitoring
helm upgrade --install \
--create-namespace \
--namespace ${NS} \
prometheus prometheus-community/kube-prometheus-stack \
--version 55.5.1 \
--set grafana.adminPassword=$(pwgen -s 20 1)
```

## Elastic

```shell
helm repo add elastic https://helm.elastic.co
helm repo update
helm search repo elastic/elasticsearch
helm show values elastic/elasticsearch

NS=elastic-system
helm upgrade --install \
--create-namespace \
--namespace ${NS} \
elastic elastic/elasticsearch \
--version 7.17.3 \
-f ./elastic/elasticsearch.yaml
```

## Cert manager

```shell
helm repo add jetstack https://charts.jetstack.io
helm repo update
helm search repo jetstack/cert-manager
helm show values jetstack/cert-manager

helm upgrade --install \
cert-manager jetstack/cert-manager \
--namespace cert-manager \
--create-namespace \
--version v1.13.3
```

# Usage

## reflector

```shell
kubectl annotate secret/service-password reflector.v1.k8s.emberstack.com/reflection-allowed=true -n <SOURCE_NAMESPACE>
kubectl annotate secret/service-password reflector.v1.k8s.emberstack.com/reflection-allowed-namespaces=<TARGET_NAMESPACE_1>,<TARGET_NAMESPACE_2> -n <SOURCE_NAMESPACE>
kubectl annotate secret/service-password reflector.v1.k8s.emberstack.com/reflection-auto-enabled=true -n <SOURCE_NAMESPACE>
# check
kubectl get secrets -n <TARGET_NAMESPACE_1>
```

[reflector]:<https://github.com/emberstack/kubernetes-reflector>

[kube_prometheus_stack]:<https://github.com/prometheus-community/helm-charts/tree/main/charts/kube-prometheus-stack>

[elastic_operator]:<https://artifacthub.io/packages/helm/elastic/elasticsearch>

[cert_manager]:<https://artifacthub.io/packages/helm/cert-manager/cert-manager>

[cert_manager_install]:<https://cert-manager.io/docs/installation/helm/>
