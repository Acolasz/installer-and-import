# Helm charts

* argo-cd [argo-cd install][argo_cd]
    * argocd-apps - project and application(sets)
* reflector [emberstack/kubernetes-reflector][reflector]
* metrics-server [metrics-server/metrics-server][metrics_server]
* longhorn
    * [prerequisite][longhorn_prerequisite]
    * [helm install][longhorn_install]
    * [check script][longhorn_example_script]
    * node setup:
        * [topology][longhorn_topology]
        * [cloud controller manager][longhorn_controller_manager]
        * Launch Longhorn with multiple disks
            * [launch longhorn multi disk][longhorn_launch_longhorn]
    * store resolution
        * [Lesson learned on deploying Minio with CIFS backend.][longhorn_flexvolume]
    * longhorn [monitoring][longhorn_monitoring]
* kube prometheus stack [prometheus-community/kube-prometheus-stack][kube_prometheus_stack]
    * prometheus-operator
    * prometheus-service
    * allertmanager-service
    * grafana
        * [Grafana dashboards official][grafana_dashboards]
    * Related article:
        * [Prometheus’ performance and cardinality in practice][prometheus_performance_in_practice]
        * [How relabeling in Prometheus works][prometheus_relabeling]
        * [Grafana dashboards Mixin][grafana_dashboards_mixin]
* fluent-bit [fluent/fluent-bit][fluent_bit]
    * mount PV/PVC sample: [Persistent storage for container logging using Fluent Bit and Amazon EFS][fluent_bit_pv_pvc]
* elastic-operator [elastic/elasticsearch][elastic_operator]
* cert-manager [jetstack/cert-manager][cert_manager]
    * describe [install][cert_manager_install]
* jaeger
    * operator
    * application
* redis
    * install
    * [Eviction][redis_eviction] policies
* redis-ui
    * install
* hpa-operator
    * [install][hpa_operator_install]
    * [banzaicloud-stable/hpa-operator][hpa_operator]
* postgresql/pgadmin4
    * install [PostgreSQL][postgresql]
    * install [pgAdmin 4][pgadmin4]
* sonarqube
* awx-operator
    * [awx-operator][awx_operator]
    * [How to Install Ansible AWX on Kubernetes Cluster][awx_install]
* gitlab-runner
    * [kubernetes executor][gitlab_runner_kubernetes]
* etcd
* nfs-subdir-external-provisioner [nfs-subdir-external-provisioner/nfs-subdir-external-provisioner][nfs_provisioner]
    * [How to Setup Dynamic NFS Provisioning in a Kubernetes Cluster][nfs_provisioner_sample]
* oauth2-proxy
    * [oauth2-proxy][oauth2_proxy]


# Install

## ArgoCD

```shell
REPO_NAME=argo-cd

RELEASE_NAME=argocd
CHART_NAME=argo-cd
CHART_VERSION=7.7.15

helm repo add ${REPO_NAME} https://argoproj.github.io/argo-helm
helm repo update ${REPO_NAME}
helm search repo ${REPO_NAME}/${CHART_NAME}
helm show values ${REPO_NAME}/${CHART_NAME} --version ${CHART_VERSION}

NS=argocd
helm upgrade --install ${RELEASE_NAME} \
${REPO_NAME}/${CHART_NAME} --version ${CHART_VERSION} \
--create-namespace \
--namespace ${NS}
```

### ArgoCD apps

```shell
REPO_NAME=argo-cd
CHART_NAME=argocd-apps
CHART_VERSION=2.0.2

helm repo add ${REPO_NAME} https://argoproj.github.io/argo-helm
helm repo update ${REPO_NAME}
helm search repo ${REPO_NAME}/${CHART_NAME}
helm show values ${REPO_NAME}/${CHART_NAME} --version ${CHART_VERSION}

NS=argocd-config
RELEASE_NAME=argocd-apps-projects
helm install ${RELEASE_NAME} \
${REPO_NAME}/${CHART_NAME} --version ${CHART_VERSION} \
--create-namespace \
--namespace ${NS} \
-f "${DIR}/${ENV}/projects-values.yaml"

RELEASE_NAME=argocd-apps-applicationsets

helm install ${RELEASE_NAME} \
${REPO_NAME}/${CHART_NAME} --version ${CHART_VERSION} \
--create-namespace \
--namespace ${NS} \
-f "./applicationsets-values.yaml"
```

## reflector

```shell
helm repo add emberstack https://emberstack.github.io/helm-charts
helm repo update
helm search repo emberstack/reflector
helm show values emberstack/reflector --version 7.1.216

NS=reflector
helm upgrade --install reflector \
emberstack/reflector --version 7.1.216 \
--create-namespace \
--namespace ${NS}
```

## metrics-server

```shell
helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/
helm repo update
helm search repo metrics-server/metrics-server
helm show values metrics-server/metrics-server --version 3.11.0

NS=metrics-server
helm upgrade --install metrics-server \
metrics-server/metrics-server --version 3.11.0 \
--create-namespace \
--namespace ${NS} \
-f ./metrics-server/default-values.yaml
```

## Longhorn

```shell
helm repo add longhorn https://charts.longhorn.io
helm repo update
helm search repo longhorn/longhorn
LONGHORN_VERSION=1.5.3
helm show values longhorn/longhorn --version ${LONGHORN_VERSION}

kubectl apply -f ./longhorn/preinstall/longhorn-iscsi-installation-1.5.3.yaml

kubectl label --overwrite node/k3s-infra-1 node.longhorn.io/create-default-disk=config topology.kubernetes.io/zone='A-site'
kubectl annotate --overwrite node/k3s-infra-1 node.longhorn.io/default-disks-config='[{ "path":"/data-persistent/longhorn", "allowScheduling":true, "storageReserved":20971520 }]'
kubectl label --overwrite node/k3s-infra-2 node.longhorn.io/create-default-disk=config topology.kubernetes.io/zone='B-site'
kubectl annotate --overwrite node/k3s-infra-2 node.longhorn.io/default-disks-config='[{ "path":"/data-persistent/longhorn", "allowScheduling":true, "storageReserved":20971520 }]'
kubectl label --overwrite node/k3s-worker-1 node.longhorn.io/create-default-disk=config topology.kubernetes.io/zone='C-site'
kubectl annotate --overwrite node/k3s-worker-1 node.longhorn.io/default-disks-config='[{ "path":"/data-persistent/longhorn", "allowScheduling":true, "storageReserved":20971520 }]'

LONGHORN_VERSION=1.5.3
NS=longhorn-system
helm upgrade --install longhorn \
longhorn/longhorn --version ${LONGHORN_VERSION} \
--create-namespace \
--namespace ${NS} \
-f ./longhorn/default-values.yaml

LONGHORN_VERSION=1.5.3
NS=longhorn-system
helm diff upgrade \
longhorn longhorn/longhorn \
--namespace ${NS} \
--version ${LONGHORN_VERSION} \
-f ./longhorn/default-values.yaml

# DaemonSet
kubectl get ds -n longhorn-system
longhorn-manager           3 3 3 3 3
longhorn-csi-plugin        3 3 3 3 3
engine-image-ei-68f17757   3 3 3 3 3
# StatefulSets
kubectl get statefulsets.apps -n longhorn-system
No resources found in default namespace.
# Deployment
kubectl get deployments.apps -n longhorn-system

longhorn-ui                1/1 1 1
longhorn-driver-deployer   1/1 1 1
csi-resizer                3/3 3 3 
csi-snapshotter            3/3 3 3
csi-attacher               3/3 3 3
csi-provisioner            3/3 3 3

kubectl apply -f ./longhorn/storageclasses/
kubectl apply -f ./longhorn/rc/
```

## Longhorn monitoring

```shell
helm repo add someblackmagic https://someblackmagic.github.io/helm-charts/
helm repo update
helm search repo someblackmagic/longhorn-monitoring
helm show values someblackmagic/longhorn-monitoring --version 1.3.0

helm template \
someblackmagic/longhorn-monitoring --version 1.0.0 \
-f ./longhorn/monitoring/monitoring-default-values.yaml

NS=longhorn-system
helm upgrade --install longhorn-monitoring \
someblackmagic/longhorn-monitoring --version 1.0.0 \
--create-namespace \
--namespace ${NS} \
-f ./longhorn/monitoring/monitoring-default-values.yaml

helm upgrade --install longhorn-monitoring \
someblackmagic/longhorn-monitoring --version 1.3.0 \
--create-namespace \
--namespace ${NS} \
-f ./longhorn/monitoring/monitoring-default-values.yaml
```

## kube prometheus stack

```shell
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm search repo prometheus-community/kube-prometheus-stack
helm show values prometheus-community/kube-prometheus-stack --version 55.5.1

NS=monitoring
helm upgrade --install prometheus \
prometheus-community/kube-prometheus-stack --version 55.5.1 \
--create-namespace \
--namespace ${NS} \
--set grafana.adminPassword=BQ5AnZgsdPmP0NsfKYD7 \
-f ./monitoring/default-values.yaml

kubectl get secrets/prometheus-grafana --template='{{index .data "admin-password"}}' -n monitoring | base64 -D

NS=monitoring
helm diff upgrade prometheus \
prometheus-community/kube-prometheus-stack --version 55.5.1 \
--namespace ${NS} \
-f ./monitoring/default-values.yaml
```

## Fluent-bit

```shell
helm repo add fluent https://fluent.github.io/helm-charts
helm repo update fluent
helm search repo fluent/fluent-bit
helm show values fluent/fluent-bit --version 0.46.10

NS=fluent-bit
helm upgrade --install fluent-bit \
fluent/fluent-bit --version 0.46.10 \
--create-namespace \
--namespace ${NS} \
-f ./fluent/infra-values.yaml
```

## Elastic

```shell
helm repo add elastic https://helm.elastic.co
helm repo update
helm search repo elastic/elasticsearch
helm show values elastic/elasticsearch --version 7.17.3

NS=elastic-system
helm upgrade --install elastic \
elastic/elasticsearch --version 7.17.3 \
--create-namespace \
--namespace ${NS} \
-f ./elastic/elasticsearch.yaml
```

## Cert manager

```shell
helm repo add jetstack https://charts.jetstack.io
helm repo update
helm search repo jetstack/cert-manager
helm show values jetstack/cert-manager --version v1.13.3

NS=cert-manager
helm upgrade --install cert-manager \
jetstack/cert-manager --version v1.13.3 \
--create-namespace \
--namespace ${NS} \
--set installCRDs=true
```

## Jaeger

### Operator install

```shell
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
helm repo update
helm search repo jaegertracing/jaeger-operator
helm show values jaegertracing/jaeger-operator --version 2.49.0

NS=observability
helm upgrade --install jaeger-operator \
jaegertracing/jaeger-operator --version v2.49.0 \
--create-namespace \
--namespace ${NS}
```

### Jaeger install

```shell
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
helm repo update
helm search repo jaegertracing/jaeger
helm show values jaegertracing/jaeger --version 0.73.1

NS=jaeger
helm upgrade --install jaeger \
jaegertracing/jaeger --version v0.73.1 \
--create-namespace \
--namespace ${NS} \
--set provisionDataStore.cassandra=false \
--set provisionDataStore.elasticsearch=true \
--set storage.type=elasticsearch
```

### Redis install

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm search repo bitnami/redis
helm show values bitnami/redis --version 18.6.2 

NS=redis
helm upgrade --install redis-cache \
bitnami/redis --version 18.6.2 \
--create-namespace \
--namespace ${NS}
```

### Redis UI install

```shell
REPO_NAME=https://github.com/patrikx3/redis-ui/tree/master/k8s/chart

NS=redis
helm upgrade --install redis-cache \
REPO_NAME/redis \
--create-namespace \
--namespace ${NS}
```

### HPA Operator

> Already not used!

```shell
helm repo add banzaicloud-stable https://kubernetes-charts.banzaicloud.com
helm repo update
helm search repo banzaicloud-stable/hpa-operator
helm show values banzaicloud-stable/hpa-operator --version 0.4.0

NS=hpa-operator
helm upgrade --install hpa-operator \
banzaicloud-stable/hpa-operator --version 0.4.0 \
--create-namespace \
--namespace ${NS}
```

## MinIO

### MinIO - MinIO Operator with Tenant console

> Not use!

```shell
helm repo add minio-operator https://operator.min.io
helm repo update
helm search repo minio-operator/operator
helm show values minio-operator/operator --version 5.0.11

NS=minio-operator
helm upgrade --install minio-operator \
minio-operator/operator --version 5.0.11 \
--create-namespace \
--namespace ${NS}
```

### MinIO - MinIO Bucket console

* Release

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm search repo bitnami/minio
helm show values bitnami/minio --version 13.2.0

NS=minio
MINIO_ACCESSKEY=admin
MINIO_SECRETKEY=admin
helm upgrade --install minio \
bitnami/minio --version 13.2.0 \
--create-namespace \
--namespace ${NS} \
--set auth.rootUser=${MINIO_ACCESSKEY} \
--set auth.rootPassword=${MINIO_SECRETKEY} \
-f ./minio/default-values.yaml
```

* Backup

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm search repo bitnami/minio
helm show values bitnami/minio --version 13.2.0

NS=minio-backup
MINIO_ACCESSKEY=admin
MINIO_SECRETKEY=backup1234
helm upgrade --install minio-backup \
bitnami/minio --version 13.2.0 \
--create-namespace \
--namespace ${NS} \
--set auth.rootUser=${MINIO_ACCESSKEY} \
--set auth.rootPassword=${MINIO_SECRETKEY}
```

## Postgresql

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm search repo bitnami/postgresql
helm show values bitnami/postgresql --version 13.4.1

NS=postgres
helm upgrade --install postgres \
bitnami/postgresql --version 13.4.1 \
--create-namespace \
--namespace ${NS} \
--set auth.enablePostgresUser=true \
--set auth.postgresPassword=postgres \
--set auth.username=longhorn \
--set auth.password=longhorn \
--set auth.database=longhorn \
-f ./postgresql/default-values.yaml
```

### PGAdmin4

```shell
helm repo add runix https://helm.runix.net
helm repo update
helm search repo runix/pgadmin4
helm show values runix/pgadmin4 --version 1.35.0

NS=postgresql
helm upgrade --install pgadmin4 \
runix/pgadmin4 --version 1.35.0 \
--create-namespace \
--namespace ${NS} \
--set env.email=admin@kukutyin.hu \
--set env.password=admin \
-f ./postgresql/default-pgadmin-values.yaml
```

## SonarQube

```shell
helm repo add sonarqube https://SonarSource.github.io/helm-chart-sonarqube
helm repo update
helm search repo sonarqube/sonarqube
helm show values sonarqube/sonarqube --version 10.3.0+2009

NS=sonarqube
helm upgrade --install sonarqube \
sonarqube/sonarqube --version 10.3.0+2009 \
--create-namespace \
--namespace ${NS} \
-f ./sonarqube/default-values.yaml
```

## awx-operator

```shell
helm repo add awx-operator https://ansible.github.io/awx-operator/
helm repo update
helm search repo awx-operator
helm show values awx-operator/awx-operator --version 2.10.0

NS=awx-operator
helm upgrade --install awx-operator \
awx-operator/awx-operator --version 2.10.0 \
--create-namespace \
--namespace ${NS}
```

## Dynamic nfs-provisioning in a kubernetes

```shell
helm repo add nfs-subdir-external-provisioner https://kubernetes-sigs.github.io/nfs-subdir-external-provisioner
helm repo update
helm search repo nfs-subdir-external-provisioner
helm show values nfs-subdir-external-provisioner/nfs-subdir-external-provisioner --version 4.0.18

NS=nfs-provisioner
helm upgrade --install nfs-provisioner \
nfs-subdir-external-provisioner/nfs-subdir-external-provisioner --version 4.0.18 \
--create-namespace \
--namespace ${NS} \
--set nfs.server=<NFS_server_IP_address> \
--set nfs.path=<nfs_server_dir> \
--set storageClass.onDelete=true
```

## OAuth2 Proxy

```shell
helm repo add oauth2-proxy https://oauth2-proxy.github.io/manifests
helm repo update
helm search repo oauth2-proxy
helm show values oauth2-proxy/oauth2-proxy --version 7.12.8

NS=monitoring
helm upgrade --install alertmanager-proxy \
oauth2-proxy/oauth2-proxy --version 7.12.8 \
--create-namespace \
--namespace ${NS}
-f ./sonarqube/default-values.yaml
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

## gitlab-runner

```shell
helm repo add gitlab https://charts.gitlab.io
helm search repo gitlab
helm search repo gitlab/gitlab-runner
helm show values gitlab/gitlab-runner --version 0.54.0

NS=gitlab-runner
helm upgrade --install runner-hugo-testing \
gitlab/gitlab-runner --version 0.54.0 \
--create-namespace \
--namespace ${NS} \
-f ./gitlab-runner/default-values.yaml
```

## etcd

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm search repo bitnami/etcd
helm show values bitnami/etcd --version 8.8.3
```

[argo_cd]:<https://argo-cd.readthedocs.io/en/stable/operator-manual/installation/#helm>

[reflector]:<https://github.com/emberstack/kubernetes-reflector>

[metrics_server]:<https://artifacthub.io/packages/helm/metrics-server/metrics-server>

[kube_prometheus_stack]:<https://github.com/prometheus-community/helm-charts/tree/main/charts/kube-prometheus-stack>

[grafana_dashboards]:<https://grafana.com/grafana/dashboards/>

[grafana_dashboards_mixin]:<https://github.com/monitoring-mixins/website/tree/master/assets>

[prometheus_performance_in_practice]:<https://medium.com/@dotdc/prometheus-performance-and-cardinality-in-practice-74d5d9cd6230>

[prometheus_relabeling]:<https://grafana.com/blog/2022/03/21/how-relabeling-in-prometheus-works/>

[fluent_bit]:<https://artifacthub.io/packages/helm/fluent/fluent-bit>

[fluent_bit_pv_pvc]:<https://aws.amazon.com/blogs/storage/persistent-storage-for-container-logging-using-fluent-bit-and-amazon-efs/>

[elastic_operator]:<https://artifacthub.io/packages/helm/elastic/elasticsearch>

[cert_manager]:<https://artifacthub.io/packages/helm/cert-manager/cert-manager>

[cert_manager_install]:<https://cert-manager.io/docs/installation/helm/>

[redis_eviction]:<https://redis.io/docs/reference/eviction/>

[hpa_operator]:<https://github.com/banzaicloud/hpa-operator>

[hpa_operator_install]:<https://artifacthub.io/packages/helm/banzaicloud-stable/hpa-operator>

[longhorn_prerequisite]:<https://github.com/longhorn/longhorn/blob/master/deploy/prerequisite/longhorn-iscsi-installation.yaml>

[longhorn_install]:<https://longhorn.io/docs/1.5.3/deploy/install/install-with-helm/>

[longhorn_example_script]:<https://github.com/longhorn/longhorn/tree/master/examples>

[longhorn_topology]:<https://kubernetes.io/docs/reference/labels-annotations-taints/#topologykubernetesiozone>

[longhorn_controller_manager]:<https://kubernetes.io/docs/reference/labels-annotations-taints/#volumes-kubernetes-io-controller-managed-attach-detach>

[longhorn_launch_longhorn]:<https://longhorn.io/docs/1.5.3/advanced-resources/default-disk-and-node-config/#launch-longhorn-with-multiple-disks>

[longhorn_flexvolume]:<https://maulana.id/soft-dev/2021--05--07--10--lesson-learned-on-deploying-minio-with-cifs-backend-part-1/>

[longhorn_monitoring]:<https://artifacthub.io/packages/helm/someblackmagic/longhorn-monitoring>

[postgresql]:<https://artifacthub.io/packages/helm/bitnami/postgresql>

[pgadmin4]:<https://artifacthub.io/packages/helm/runix/pgadmin4>

[awx_operator]:<https://ansible.github.io/awx-operator/>

[awx_install]:<https://www.linuxtechi.com/install-ansible-awx-on-kubernetes-cluster/#google_vignette>

[gitlab_runner_kubernetes]:<https://docs.gitlab.com/runner/executors/kubernetes/>

[nfs_provisioner]:<https://artifacthub.io/packages/helm/nfs-subdir-external-provisioner/nfs-subdir-external-provisioner>

[nfs_provisioner_sample]:<https://hbayraktar.medium.com/how-to-setup-dynamic-nfs-provisioning-in-a-kubernetes-cluster-cbf433b7de29>

[oauth2_proxy]:<https://oauth2-proxy.github.io/oauth2-proxy/>
