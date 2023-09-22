# Tools with brew

## commands

### brew package manager

Install in [this site][brew_package_manager_install]

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

```shell
brew list
```

## tools list

### Formulae

* [colima][colima_install] `brew install colima`. Usage [Apple M1 (ARM64)][colima_docker]
* kubectx
* kubernetes-cli
* jq
* yq
* helm
* midnight-commander
* parallel
* [qemu][qemu]
* sysbench

### Casks

* kubecontext
* google-cloud-sdk

[colima_install]:<https://github.com/abiosoft/colima#installation>

[colima_docker]:<https://hub.docker.com/r/gvenzl/oracle-xe>

[brew_package_manager_install]:<https://brew.sh/>

[qemu]:<../commands/qemu/qemu.md>
