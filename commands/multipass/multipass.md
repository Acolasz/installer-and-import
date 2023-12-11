# Usage

> Multipass is a CLI for Ubuntu VM manage tool.

## Install

The Multipass command tool [install][multipass_install]

## Commands

### General cmd

```shell
multipass find
multipass info <image_name>
```

### Enable ssh

```shell
multipass list
multipass launch --name <image_name> --cloud-init cloud-init.yaml
multipass launch --name <image_name> --cpus 2 --disk 15G --memory 4G --mount <local-path>:<instance-path>
```

# Related articles

* [Enable ssh access to multipass vms][multipass_anable_ssh]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[multipass_install]:<https://multipass.run/install>

[multipass_anable_ssh]:<https://dev.to/arc42/enable-ssh-access-to-multipass-vms-36p7>
