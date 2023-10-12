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
helm repo add $REPO_NAME <url> --username <user> --password <userpassword>
helm repo update $REPO_NAME

helm search repo $REPO_NAME
helm search repo $CHART_NAME
helm search repo $REPO_NAME/$CHART_NAME

helm pull $REPO_NAME/$CHART_NAME
helm pull $REPO_NAME/$CHART_NAME --version 12.8.2

helm show all $REPO_NAME/$CHART_NAME

# registry
helm registry login https://artifactory.com/artifactory/helm-manual-local/ --username oo --password AKCp8k7uYNRRWDsRniSMzBuyTkKsdcEwCkunLaoVb1gu5Vwj67oogcKnMBf24MHAxwTEL6x2f
// artifactory plugin install and usage
helm repo add helm-manual-local https://artifactory.com/artifactory/helm-manual-local/ --username oo --password AKCp8k7uYNRRWDsRniSMzBuyTkKsdcEwCkunLaoVb1gu5Vwj67oogcKnMBf24MHAxwTEL6x2f
helm repo add helm-manual-local https://artifactory.com/artifactory/helm-manual-local/ --username oo --password AKCp8k7uYNRRWDsRniSMzBuyTkKsdcEwCkunLaoVb1gu5Vwj67oogcKnMBf24MHAxwTEL6x2f
# plugin
helm plugin install https://github.com/belitre/helm-push-artifactory-plugin --version 1.0.1
helm plugin list
helm push-artifactory $CHART_NAME-1.0.0.tgz helm-manual-local
```

### Local

```shell
CHART_NAME=
helm lint -f ./override-values.yaml ./$CHART_NAME
helm template -f ./override-values.yaml ./$CHART_NAME
helm package ./$CHART_NAME

# --dry-run
helm install -f values.yaml -f override.yaml $CHART_NAME ./$CHART_NAME --dry-run
helm install $CHART_NAME -f ./override-values.yaml ./$CHART_NAME-1.0.0.tgz --dry-run

helm upgrade --install -n $CHART_NAMESPACE $CHART_NAME repo/$CHART_NAME -f ./override-values.yaml --version $CHART_VERSION --dry-run
```

### Remote

```shell
CHART_NAME=
CHART_VERSION=
CHART_NAMESPACE=
# install
helm install -f ./values.yaml -f ./override-values.yaml $CHART_NAME ./$CHART_NAME
helm install --create-namespace -n $CHART_NAMESPACE -f values.yaml -f override.yaml $CHART_NAME ./$CHART_NAME
helm install $CHART_NAME -f ./override-values.yaml ./$CHART_NAME-1.0.0.tgz

# upgrade
helm upgrade --install --create-namespace -n $CHART_NAMESPACE $CHART_NAME repo/$CHART_NAME -f ./override-values.yaml --version $CHART_VERSIONhelm upgrade --install -n $CHART_NAMESPACE $CHART_NAME repo/$CHART_NAME -f ./override-values.yaml --version $CHART_VERSION

# uninstall
helm unistall -n $CHART_NAMESPACE $CHART_NAME

# get, list
helm list
helm get all $CHART_NAME
```

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[helm_install]:<https://helm.sh/docs/intro/install/>