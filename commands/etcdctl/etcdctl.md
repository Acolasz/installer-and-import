#

##

##

```shell
ADVERTISE_URL="https://192.168.105.2:2379"
ETCDCTL_API=3 etcdctl --endpoints $ADVERTISE_URL \
--cacert /var/lib/minikube/certs/etcd/ca.crt \
--cert /var/lib/minikube/certs/etcd/server.crt \
--key /var/lib/minikube/certs/etcd/server.key \
get / --prefix --keys-only
```

```shell
etcd --advertise-client-urls=https://192.168.105.2:2379 \
--cert-file=/var/lib/minikube/certs/etcd/server.crt \
--client-cert-auth=true \
--data-dir=/var/lib/minikube/etcd \
--experimental-initial-corrupt-check=true \
--experimental-watch-progress-notify-interval=5s \
--initial-advertise-peer-urls=https://192.168.105.2:2380 \
--initial-cluster=minikube=https://192.168.105.2:2380 \
--key-file=/var/lib/minikube/certs/etcd/server.key \
--listen-client-urls=https://127.0.0.1:2379,https://192.168.105.2:2379 \
--listen-metrics-urls=http://127.0.0.1:2381 \
--listen-peer-urls=https://192.168.105.2:2380 \
--name=minikube \
--peer-cert-file=/var/lib/minikube/certs/etcd/peer.crt \
--peer-client-cert-auth=true \
--peer-key-file=/var/lib/minikube/certs/etcd/peer.key \
--peer-trusted-ca-file=/var/lib/minikube/certs/etcd/ca.crt \
--proxy-refresh-interval=70000 \
--snapshot-count=10000 \
--trusted-ca-file=/var/lib/minikube/certs/etcd/ca.crt
```
