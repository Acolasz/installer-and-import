# Usage

> Helmfile is a helm chart wrapper for Kubernetes.

## Install

The helmfile cli [install][helmfile_install] from Homebrew (macOS).

```shell
helmfile version

▓▓▓ helmfile

  Version            v0.157.0
  Git Commit         "brew"
  Build Date         09 Sep 23 16:06 CEST (1 month ago)
  Commit Date        09 Sep 23 16:06 CEST (1 month ago)
  Dirty Build        no
  Go version         1.21.1
  Compiler           gc
  Platform           darwin/arm64
```

## Commands

### helmfile.yaml format

```yaml
repositories:
  - name: prometheus-community
    url: https://prometheus-community.github.io/helm-charts

releases:
  - name: prom-norbac-ubuntu
    namespace: prometheus
    chart: prometheus-community/prometheus
    set:
      - name: rbac.create
        value: false
```

```shell
helmfile init
helmfile sync
helmfile apply
```

# Related article

* GitHub: [helmfile][helmfile_github]
* Docs: [Helmfile - Deploy Kubernetes Helm Charts][helmfile_docs]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[helmfile_install]:<https://formulae.brew.sh/formula/helmfile>

[helmfile_github]:<https://github.com/helmfile/helmfile>

[helmfile_docs]:<https://helmfile.readthedocs.io/en/latest/>
