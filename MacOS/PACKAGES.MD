# Tools with brew

## commands

### brew package manager

Install in [this site][brew_package_manager_install]

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

## [Brewfile][brew_brewfile]

```shell
# Create Brewfile
brew bundle dump --file=~/workspace/github/acolasz/installer-and-import/MacOS/Brewfile
# Install from Brewfile
brew bundle install -file=~/workspace/github/acolasz/installer-and-import/MacOS/Brewfile
```

## List

```shell
brew list
```

```shell
brew deps --tree colima
```

> [leaves][brew_leaves]
> ```shell
> brew_leaves
> ```

### Formulae

* [colima][colima] `brew install colima`. Usage [Apple M1 (ARM64)][colima_install]
* kubectx
    * `brew install kubectx`
* [kubeseal][kubeseal] - "Sealed Secrets" for Kubernetes
    * `brew install kubeseal`
* kubernetes-cli
* [helm][helm_install]
    * `brew install helm`
    * [helmfile][helmfile_install]
        * `brew install helmfile`
* [kustomize][kustomize_install]
    * `brew install kustomize`
* k3sup
    * `brew install k3sup`
    * [k3s usage][k3s]
* k9s
    * `brew install k9s`
    * [k9s Cheatsheet][k9s_cheatsheets]
* popeye
    * `brew install derailed/popeye/popeye`
    * [Popeye][kubernetes_popeye]
* ~~midnight-commander~~
    * `brew install midnight-commander`
* parallel
    * `brew install parallel`
* ~~[qemu][qemu_install]~~
    * `brew install qemu`
* [sysbench][sysbench_install]
* ~~libvirt~~
* ~~virt-manager~~
* ~~xorriso~~
* ~~[multipass][multipass_install]~~
* gnupg (gpg)
    * `brew install gnupg`
* [etcdctl][etcdctl_install]
    * `brew install etcd`
* [yaml-json][yaml_json_install]
    * `brew install yq`
    * `brew install yamllint`
    * `brew install jq`
* pwgen
    * `brew install pwgen`
* sshpass
    * `brew install esolitos/ipa/sshpass`
* telnet
    * `brew install telnet`
* python-tk
    * `brew install python-tk`
* arkede
    * `curl -sLS https://get.arkade.dev | sudo sh`
* direnv
    * `brew install direnv`
* jid
    * `brew install jid`
* [iperf3][iperf3] - measure bandwith
    * `brew install iperf3`
* wget
    * `brew install wget`
* go-task
    * `brew install go-task`
    * [Task][go_task]
* zsh-completions
    * `brew install zsh-completions`
* github cli
    * `brew install gh`
* docker
    * `brew install --cask docker`
    * `brew install docker-compose`
* ansible
    * `brew install ansible`
    * dependencies:
        * `certifi`
        * `pycparser`
        * `cffi`
        * `cryptography`
        * `libyaml`
* nmap
    * `brew install nmap`
    * `echo "Hello, Kubernetes!" | ncat -u <node-external-ip> <nodeport>`
* pv
    * `brew install pv`
    * [pv command][pv_cmd]
* [tmux][tmux_install] multiplexer (multiple terminal in one)
    * `brew install tmux`
    * [Usage tmux][tmux_usage]
    * [Cheat sheet][tmux_cheatsheet]
    * related cmd: [ClusterSSH][tmux_cssh]
    * [Synchronize panes][tmux_synchronize_panes]
* groovy
    * `brew install groovy`
    * dependencies:
        * `freetype`
        * `gifli`
        * `bfontconfig`
        * `glib` `xorgproto`
        * `libxau`
        * `libxdmcp`
        * `libxcb`
        * `libx11`
        * `libxext`
        * `libxrender`
        * `cairo`
        * `graphite2`
        * `icu4c@77`
        * `harfbuzz`
        * `libtiff`
        * `little-cms2`
        * `openjdk`
* bash
    * `brew install bash`
* sqlplus
    * `brew tap InstantClientTap/instantclient`
    * `brew install instantclient-basic`
    * `brew install instantclient-sqlplus`
* kubelogin
    * `brew install kubelogin`

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

[yaml_json_install]:<../commands/yaml-json/yaml-json.md>

[python]:<../Python/python.md>

[iperf3]:<https://www.techtarget.com/searchnetworking/tip/How-to-use-iPerf-to-measure-throughput>

[k3s]:<../kubernetes/k3s/k3s.md>

[k9s_cheatsheets]:<https://www.hackingnote.com/en/cheatsheets/k9s/>

[kubernetes_popeye]:<https://github.com/derailed/popeye>

[go_task]:<https://taskfile.dev/usage/>

[kubeseal]:<https://github.com/bitnami-labs/sealed-secrets?tab=readme-ov-file#homebrew>

[pv_cmd]:<https://www.cyberciti.biz/open-source/command-line-hacks/pv-command-examples/>

[brew_brewfile]:<https://gist.github.com/ChristopherA/a579274536aab36ea9966f301ff14f3f>

[brew_leaves]:<https://thoughtbot.com/blog/brew-leaves>

[tmux_install]:<https://github.com/tmux/tmux/wiki/Installing>

[tmux_usage]:<https://github.com/tmux/tmux/wiki>

[tmux_cheatsheet]:<https://tmuxcheatsheet.com/>

[tmux_cssh]:<https://github.com/peikk0/tmux-cssh>

[tmux_synchronize_panes]:<https://medium.com/@thehackadda/synchronize-panes-in-tmux-5cd6bc54ca83>
