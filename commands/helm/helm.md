# Usage

> Helm is the package manager for Kubernetes.

## Install

The Helm cli [install][helm_install] from Homebrew (macOS).

```shell
helm version

$ version.BuildInfo{Version:"v3.12.3", GitCommit:"3a31588ad33fe3b89af5a2a54ee1d25bfe6eaa5e", GitTreeState:"clean", GoVersion:"go1.20.7"}
```

## Commands

### Remote repository

```shell
CHART_NAME=
REPO_NAME=
helm repo add ${REPO_NAME} <url> --username <user> --password <userpassword>
helm repo update ${REPO_NAME}

helm search repo ${REPO_NAME}
helm search repo ${CHART_NAME}
helm search repo ${REPO_NAME}/${CHART_NAME}
# all versions
helm search repo -l ${REPO_NAME}/${CHART_NAME}
helm search repo --versions ${REPO_NAME}/${CHART_NAME}
# versions with regexp
helm search repo -r ${REPO_NAME}/*
helm search repo --regexp ${REPO_NAME}/*


helm pull ${REPO_NAME}/${CHART_NAME}
helm pull ${REPO_NAME}/${CHART_NAME} --version 12.8.2
helm pull --untar bitnami/redis --version 17.3.9
helm pull --untar --untardir ${CHART_NAME}-12.8.2 ${REPO_NAME}/${CHART_NAME} --version 12.8.2

# tar -xzvf ./${CHART_NAME}-12.8.2.tar.gz -C ./${CHART_NAME}

helm show all ${REPO_NAME}/${CHART_NAME}

# registry
helm registry login https://artifactory.com/artifactory/helm-manual-local/ --username oo --password <token>
// artifactory plugin install and usage
helm repo add helm-manual-local https://artifactory.com/artifactory/helm-manual-local/ --username oo --password <token>
helm repo add helm-manual-local https://artifactory.com/artifactory/helm-manual-local/ --username oo --password <token>
# plugin
helm plugin install https://github.com/belitre/helm-push-artifactory-plugin --version 1.0.1
helm plugin list
helm push-artifactory ${CHART_NAME}-1.0.0.tgz helm-manual-local
```

### Local

```shell
CHART_NAME=
CHART_NAMESPACE=
helm lint -f ./override-values.yaml ./${CHART_NAME}
helm template -f ./override-values.yaml ./${CHART_NAME}
helm package ./${CHART_NAME}

# --dry-run
helm install -f values.yaml -f override.yaml ${CHART_NAME} ./${CHART_NAME} --dry-run
helm install ${CHART_NAME} -f ./override-values.yaml ./${CHART_NAME}-1.0.0.tgz --dry-run

helm upgrade --install -n ${CHART_NAMESPACE} ${CHART_NAME} repo/${CHART_NAME} -f ./override-values.yaml --version ${CHART_VERSION} --dry-run
```

### Remote

```shell
helm repo add helm-manual-local https://artifactory.com/artifactory/helm-manual-local/ --username oo --password <token>
cat psw.txt | helm repo add helm-manual-local https://artifactory.com/artifactory/helm-manual-local/ --username oo --password-stdin
```

#### repositories.yaml

```shell
apiVersion: ""
generated: "0001-01-01T00:00:00Z"
repositories:
- caFile: ""
  certFile: ""
  insecure_skip_tls_verify: false
  keyFile: ""
  name: helm-release
  pass_credentials_all: false
  password: 'password'
  url: https://artifactory.kktyn.hu/repository/helm-release
  username: username
- caFile: ""
  certFile: ""
  insecure_skip_tls_verify: false
  keyFile: ""
  name: helm-snapshot
  pass_credentials_all: false
  password: 'password'
  url: https://nexus.kktyn.hu/repository/helm-snapshot
  username: username
```

```shell
CHART_NAME=
CHART_VERSION=
CHART_NAMESPACE=
# install
helm install -f ./values.yaml -f ./override-values.yaml ${CHART_NAME} ./${CHART_NAME}
helm install --create-namespace -n ${CHART_NAMESPACE} -f values.yaml -f override.yaml ${CHART_NAME} ./${CHART_NAME}
helm install ${CHART_NAME} -f ./override-values.yaml ./${CHART_NAME}-1.0.0.tgz

# upgrade
helm upgrade --install --create-namespace -n ${CHART_NAMESPACE} ${CHART_NAME} repo/${CHART_NAME} -f ./override-values.yaml --version ${CHART_VERSION}
helm upgrade --install -n ${CHART_NAMESPACE} $CHART_NAME repo/${CHART_NAME} -f ./override-values.yaml --version ${CHART_VERSION}

# uninstall
helm unisntall -n ${CHART_NAMESPACE} ${CHART_NAME}

# get, list
helm list
helm get all ${CHART_NAME}
helm get values ${CHART_NAME}
```

# Rollback

```shell
CHART_NAME=
CHART_NAMESPACE=
helm rollback ${CHART_NAME} <revision_number> -n ${CHART_NAMESPACE}
```

# History

```shell
helm history -n <namespace> <release_name>
```

## Plugins

```shell
helm plugin -h
helm plugin list
helm plugin install "name/repo"
```

* [helm_diff][helm_diff]
    * `helm plugin install https://github.com/databus23/helm-diff`
* [unittest][unittest]
    * `helm plugin install https://github.com/helm-unittest/helm-unittest.git`
    * [helm-unittest][helm_plugin_unittest]

# Helm Hooks

[Hooks:][helm_hooks]

* **pre-install**
* **post-install**
* **pre-upgrade**
* **post-upgrade**
* **test**

## Implements:

```yaml
metadata:
  name: "{{ .Release.Name }}"
  annotations:
    "helm.sh/hook": post-install
    "helm.sh/hook-weight": "-5"
    "helm.sh/hook-delete-policy": hook-succeeded
```

# Post renderer

Helm and Kustomize together [helm ... --post-render][post-render]

# Related article

* Helm hook - [Options available for Health Checks with Helm Charts][helm_hooks]
* helm charts:
    * [bitnami charts][bitnami_charts]
    * [cetic charts][cetic_charts]
    * [emberstack charts][emberstack_charts]
    * [prometheus community charts][prometheus_community_charts]
    * [elastic.co charts][elastic_charts]
    * [jetstack.io][jetstack_charts]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[helm_install]:<https://helm.sh/docs/intro/install/>

[helm_hooks]:<https://chrisharwell94.medium.com/options-available-for-health-checks-with-helm-charts-b139f26f70aa>

[post_render]:<https://github.com/thomastaylor312/advanced-helm-demos/tree/master/post-render>

[helm_diff]:<https://github.com/databus23/helm-diff>

[unittest]:<https://github.com/helm-unittest/helm-unittest>

[cetic_charts]:<https://github.com/cetic/helm-charts>

[bitnami_charts]:<https://charts.bitnami.com/bitnami>

[emberstack_charts]:<https://github.com/emberstack/helm-charts>

[prometheus_community_charts]:<https://github.com/prometheus-community/helm-charts/tree/main/charts>

[elastic_charts]:<https://helm.elastic.co>

[jetstack_charts]:<https://charts.jetstack.io>

[helm_plugin_unittest]:<https://github.com/helm-unittest/helm-unittest>
