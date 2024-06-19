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

### Formulae

* [colima][colima] `brew install colima`. Usage [Apple M1 (ARM64)][colima_install]
* kubectx
* kubernetes-cli
* jq
* yq
* [helm][helm_install]
* midnight-commander
* parallel
* [qemu][qemu_install]
* [sysbench][sysbench_install]
* libvirt
* virt-manager
* xorriso
* [multipass][multipass_install]
* gnupg (gpg)
* [etcdctl][etcdctl_install]
* [kustomize][kustomize_install]
* [helmfile][helmfile_install]
* [yamllint][yamllint_install]
* pwgen
  * `brew install pwgen`
* sshpass
  * `brew install esolitos/ipa/sshpass`
* telnet
  * `brew install telnet`
* [python@3.12][python]
  * `brew install python-tk`
* arkede
  * `curl -sLS https://get.arkade.dev | sudo sh`
* direnv
  * `brew install direnv`
* jid
  * `brew install jid`
* [iperf3][iperf3] - measure bandwith
  * `brew install iperf3`
* k3sup
  * `brew install k3sup`
  * [k3s usage][k3s]
* k9s
  * `brew install k9s`
* wget
  * `brew install wget`
* go-task
  * `brew install go-task`
  * [Task][go_task]

### Casks

* kubecontext
* google-cloud-sdk

[colima_install]:<https://github.com/abiosoft/colima#installation>

[colima]:<../commands/colima/colima.md>

[brew_package_manager_install]:<https://brew.sh/>

[qemu_install]:<../commands/qemu/qemu.md>

[sysbench_install]:<../commands/sysbench/sysbench.md>

[multipass_install]:<../commands/multipass/multipass.md>

[helm_install]:<../commands/helm/helm.md>

[etcdctl_install]:<../commands/etcdctl/etcdctl.md>

[kustomize_install]:<../commands/kustomize/kustomize.md>

[helmfile_install]:<../commands/helmfile/helmfile.md>

[yamllint_install]:<../commands/yaml-json/yaml-json.md>

[python]:<../Python/python.md>

[iperf3]:<https://www.techtarget.com/searchnetworking/tip/How-to-use-iPerf-to-measure-throughput>

[k3s]:<../kubernetes/k3s/k3s.md>

[go_task]:<https://taskfile.dev/usage/>
