#!/usr/bin/env sh

qemu-system-x86_64 \
-m 2048 \
-vga virtio \
-usb \
-device qemu-xhci \
-device usb-kbd \
-device usb-tablet \
-drive file=./img/alpine_docker.qcow2,if=virtio \
-machine pc \
-smp 4 \
-net user,hostfwd=tcp::10022-:22 \
-net nic --rtc base=utc,clock=host \
-cpu kvm64-v1 \
-accel tcg