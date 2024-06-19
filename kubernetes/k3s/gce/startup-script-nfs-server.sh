#!/usr/bin/env bash

apt-get update
apt-get install -yq python3.6 \
                    nfs-kernel-server

mkdir -p /opt/nfs
chown nobody:nogroup /opt/nfs
chmod 777 /opt/nfs

exit 0
