#!/usr/bin/env sh

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
