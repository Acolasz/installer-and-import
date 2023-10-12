# Libvirt & QEMU (QCOW2) on macOS with ARM64 M1

```shell
# start libvirt (also starts it after boot)
brew services start libvirt
```

* [Virsh Command Reference!!!][libvirt_doc]
* [How to install KVM server on Debian 9/10 Headless Server][debian_linux_install]

## Create VM's

- Download the desired Linux Server `.iso` to `./iso/`. for example: [Fedora-Workstation-Live-aarch64-38-1.6.iso][fedora38_aarch64]
- Copy the `./xml/fedora38.xml` and adjust it to your needs:
    - set a new `name` on line 2
    - set a unique `uuid` on line 3
    - set the `path` to your disk image on line 21 (will be created below)
    - set the `path` to your iso file on line 27
    - set a `port` (e.g. 5900) to connect to the vm using vnc on line 36
    - set a `port` (e.g. 2222) to connect to the vm using ssh on line 48
    - expose additional `ports` (e.g. 3000 for development servers) on line 48

```bash
# create a disk image
qemu-img create -f qcow2 ./img/fedora38.qcow2 15g

$ Formatting './img/fedora38.qcow2', fmt=qcow2 cluster_size=65536 extended_l2=off compression_type=zlib size=16106127360 lazy_refcounts=off refcount_bits=16

qemu-img info ./img/fedora38.qcow2

$ image: ./img/fedora38.qcow2
$ file format: qcow2
$ virtual size: 15 GiB (16106127360 bytes)
$ disk size: 196 KiB
$ cluster_size: 65536
$ Format specific information:
$     compat: 1.1
$     compression type: zlib
$     lazy refcounts: false
$     refcount bits: 16
$     corrupt: false
$     extended l2: false
$ Child node '/file':
$     filename: ./img/fedora38.qcow2
$     protocol type: file
$     file length: 192 KiB (197120 bytes)
$     disk size: 196 KiB

qemu-img check ./img/fedora38.qcow2

$ No errors were found on the image.
$ Image end offset: 262144
```

## virt-install

```shell
# os-variant
virt-install --osinfo list
# added network to exist VM
virsh attach-interface --domain debian12 --type network --source br0 --model virtio

```

```shell
virt-install \
--debug \
--name debian12 \
--ram 4096 \
--disk path=./img/debian.qcow2,size=20 \
--vcpus 4 \
--os-variant debian12 \
--network bridge=br0 \
--graphics none \
--console pty,target_type=serial \
--location ./iso/debian-12.2.0-arm64-DVD-1.iso \
--extra-args 'console=ttyS0,115200n8 serial'
```

## Start VM

```shell
# define and start the vm
virsh define ./xml/fedora.xml
virsh define ./xml/alpine.xml
virsh start fedora38
virsh start alpine318
```

### VNC

Use vnc to install server (localhost:5900)

## Manage VM's

```bash
# list all vm's
virsh list --all

# stop the vm
virsh shutdown fedora38
virsh shutdown alpine318

# create a new layer on top of the current layer
mv ./img/fedora38.qcow2 ./img/fedora38_01.qcow2

qemu-img create -f qcow2 -b fedora38_01.qcow2 ./img/fedora38.qcow2 -F qcow2

# restart the vm with the new layer
virsh start fedora38
virsh start alpine318
```

## Access Vm

```bash
# connect to vm and forward vm ports to localhost
cat <<EOT >> ./bin/fedora38
#!/bin/bash
ssh -p 2222 -L3000:localhost:3000 alex@localhost
EOT

chmod +x ./bin/fedora38

./bin/fedora35
```

```shell
virsh console alpine318
```

## Misc

```bash
# undefine the vm
virsh undefine fedora38 --nvram
```

## in virsh

```shell
virsh # help

virsh # net-list

virsh # exit 
```

[fedora38_aarch64]:<https://ftp.plusline.net/fedora/linux/releases/38/Workstation/aarch64/iso/Fedora-Workstation-Live-aarch64-38-1.6.iso>

[libvirt_doc]:<https://download.libvirt.org/virshcmdref/html/index.html>

[debian_linux_install]:<https://www.cyberciti.biz/faq/install-kvm-server-debian-linux-9-headless-server/>
