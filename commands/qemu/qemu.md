# Usage

> QEMU is a hardware emulator which can make use of different accelerators when running VMs.

## Commands

### list

```shell
qemu-img --version
qemu-system-aarch64 --version
qemu-system-x86_64 --version
```

#### image

```shell
qemu-img create -f qcow2 ./img/alpine.qcow2 15g
#Formatting 'alpine.qcow2', fmt=qcow2 cluster_size=65536 extended_l2=off compression_type=zlib size=16106127360 lazy_refcounts=off refcount_bits=16
qemu-img info ./img/alpine.qcow2

#image: ./img/alpine.qcow2
#file format: qcow2
#virtual size: 15 GiB (16106127360 bytes)
#disk size: 196 KiB
#cluster_size: 65536
#Format specific information:
#    compat: 1.1
#    compression type: zlib
#    lazy refcounts: false
#    refcount bits: 16
#    corrupt: false
#    extended l2: false
#Child node '/file':
#    filename: ./img/alpine.qcow2
#    protocol type: file
#    file length: 192 KiB (197120 bytes)
#    disk size: 196 KiB
    
qemu-img create -f qcow2 <image_name> 20G
```

#### system help

```shell
# arm 64
qemu-system-aarch64 -machine help
qemu-system-aarch64 -cpu help
qemu-system-aarch64 -accel help
qemu-system-aarch64 -smp help

# x86_64
qemu-system-x86_64 -machine help
qemu-system-x86_64 -cpu help
qemu-system-x86_64 -accel help
qemu-system-x86_64 -smp help
```

## Sample Linux VM
[How to run Linux on Macbook M1][qemu_alpine_to_m1]

```shell
# x86_64
qemu-system-x86_64 \
-m 2048 \
-vga virtio \
-usb \
-device qemu-xhci \
-device usb-kbd \
-device usb-tablet \
-drive file=./img/alpine.qcow2,if=virtio \
-machine pc \
-smp 4 \
-net user,hostfwd=tcp::10022-:22 \
-net nic \
-cpu qemu64 \
-accel tcg,thread=multi \
-boot d \
-cdrom ./iso/alpine-stand-3.18.3-x86_64.iso

qemu-img info ./img/alpine.qcow2

#image: ./img/alpine.qcow2
#file format: qcow2
#virtual size: 15 GiB (16106127360 bytes)
#disk size: 497 MiB
#cluster_size: 65536
#Format specific information:
#    compat: 1.1
#    compression type: zlib
#    lazy refcounts: false
#    refcount bits: 16
#    corrupt: false
#    extended l2: false
#Child node '/file':
#    filename: ./img/alpine.qcow2
#    protocol type: file
#    file length: 486 MiB (510066688 bytes)
#    disk size: 497 MiB
```

### Allow SSH root

```shell
# x86_64
qemu-system-x86_64 \
-m 2048 \
-vga virtio \
-usb \
-device qemu-xhci \
-device usb-kbd \
-device usb-tablet \
-drive file=./alpine.qcow2,if=virtio \
-machine pc \
-smp 4 \
-net user,hostfwd=tcp::10022-:22 \
-net nic --rtc base=utc,clock=host \
-cpu kvm64-v1 \
-accel tcg
```

> in alpine linux terminal
> ```shell
> echo "PermitRootLogin yes" >> /etc/ssh/sshd_config
> service sshd restart
> ```

#### test ssh connection
```shell
ssh root@127.1 -p 10022
```

# Related articles

* Official [site qemu.org][qemu_org]
* Official [Docs][qemu_docs]
* [How to run Linux on Macbook M1][qemu_alpine_to_m1] and download [alpine.iso][download_alpine_iso] file.
* github repo with [libvirt][github_qemu_libvirt]
* [Using QEMU to run Linux images on M1 Macbook][m1_qemu]
* QEMU Networking [NIC][qemu_nic]
* [Running Virtual Machines with qemu-system-ARCH][running_qemu]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[qemu_org]:<https://www.qemu.org/>

[qemu_docs]:<https://www.qemu.org/docs/master/>

[qemu_alpine_to_m1]:<https://ahmetozer.org/How-to-run-Linux-on-Macbook-M1>

[download_alpine_iso]:<https://dl-cdn.alpinelinux.org/alpine/v3.18/releases/x86_64/alpine-standard-3.18.3-x86_64.iso>

[github_qemu_libvirt]:<https://github.com/alexscheitlin/libvirt-macos-m1>

[m1_qemu]:<https://www.whexy.com/posts/m1qemu>

[qemu_nic]:<https://wiki.qemu.org/Documentation/Networking>

[running_qemu]:<https://documentation.suse.com/sles/12-SP5/html/SLES-all/cha-qemu-running.html>
