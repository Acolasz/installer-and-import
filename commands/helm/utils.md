# Helm charts

* reflector [emberstack/kubernetes-reflector][reflector]
* longhorn
    * [prerequisite][longhorn_prerequisite]
    * [helm install][longhorn_install]
    * [check script][longhorn_example_script]
    * node setup:
        * [topology][longhorn_topology]
        * [cloud controller manager][longhorn_controller_manager]
        * Launch Longhorn with multiple disks
            * [launch longhorn multi disk][longhorn_launch_longhorn]
    * store resoulution
        * [Lesson learned on deploying Minio with CIFS backend.][longhorn_flexvolume]
* kube prometheus stack [prometheus-community/kube-prometheus-stack][kube_prometheus_stack]
    * prometheus-operator
    * prometheus-service
    * allertmanager-service
    * grafana
* elastic-operator [elastic/elasticsearch][elastic_operator]
* cert-manager [jetstack/cert-manager][cert_manager]
    * describe [install][cert_manager_install]
* jaeger
    * operator
    * application
* redis
    * install
    * [Eviction][redis_eviction] policies
* hpa-operator
    * [install][hpa_operator_install]
    * [banzaicloud-stable/hpa-operator][hpa_operator]
* postgresql/pgadmin4
    * install [PostgreSQL][postgresql]
    * install [pgAdmin 4][pgadmin4]

# Install

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

kubectl apply -f ./longhorn/storageclasses/sc-s-ha.yaml
kubectl apply -f ./longhorn/storageclasses/sc-ha.yaml
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
--set grafana.adminPassword=$(pwgen -s 20 1) \
-f ./monitoring/default-values.yaml

kubectl get secrets/prometheus-grafana --template='{{index .data "admin-password"}}' -n monitoring | base64 -D
```

## Fluent-bit

```shell
helm repo add fluent https://fluent.github.io/helm-charts
helm repo update
helm search repo fluent/fluent-bit
helm show values fluent/fluent-bit --version 0.42.0

NS=fluent-bit
helm upgrade --install fluent-bit \
fluent/fluent-bit --version 0.42.0 \
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

NS=minio-release
MINIO_ACCESSKEY=admin
MINIO_SECRETKEY=minio1234
helm upgrade --install minio-release \
bitnami/minio --version 13.2.0 \
--create-namespace \
--namespace ${NS} \
--set auth.rootUser=${MINIO_ACCESSKEY} \
--set auth.rootPassword=${MINIO_SECRETKEY} \
-f ./minio/infra-values.yaml
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

NS=postgresql
helm upgrade --install postgresql \
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
helm show values runix/pgadmin4 --version 1.23.1

NS=postgresql
helm upgrade --install pgadmin4 \
runix/pgadmin4 --version 1.23.1 \
--create-namespace \
--namespace ${NS} \
--set env.email=admin@kukutyin.hu \
--set env.password=admin \
-f ./postgresql/default-pgadmin-values.yaml
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

[postgresql]:<https://artifacthub.io/packages/helm/bitnami/postgresql>

[pgadmin4]:<https://artifacthub.io/packages/helm/runix/pgadmin4>