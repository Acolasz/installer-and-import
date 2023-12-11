# Minikube on MacOS

## Install

### Setup [Minikube][minikube_install]

```shell
# check
brew list
```

## Commands

```shell
minikube version
# First clean minikube clusters
minikube delete --all
kubectx
```

### Minikube IP address

```shell
minikube ip
```

### Create new cluster

```shell
# create cluster (docker driver)
minikube start --nodes 3 -p k8scluster
# one control-plane and two worker
minikube profile list
|------------|-----------|---------|--------------|------|---------|---------|-------|--------|
|  Profile   | VM Driver | Runtime |      IP      | Port | Version | Status  | Nodes | Active |
|------------|-----------|---------|--------------|------|---------|---------|-------|--------|
| k8scluster | docker    | docker  | 192.168.49.2 | 8443 | v1.27.4 | Running |     3 |        |
|------------|-----------|---------|--------------|------|---------|---------|-------|--------|
# change between clusters
minikube profile k8scluster
# list nodes
k get nodes
NAME             STATUS   ROLES           AGE     VERSION
k8scluster       Ready    control-plane   7m15s   v1.27.4
k8scluster-m02   Ready    <none>          6m57s   v1.27.4
k8scluster-m03   Ready    <none>          6m46s   v1.27.4
# create worker label on node
kubectl label node k8scluster-m02 node-role.kubernetes.io/worker=worker
kubectl label node k8scluster-m03 node-role.kubernetes.io/worker=worker

# active profile
minikube profile
```

### Advanced

```shell
minikube start --driver qemu --network socket_vmnet
minikube start --addons=ingress --cpus=4 --cni=flannel --install-addons=true --kubernetes-version=stable --memory=8g
```

# Related article

* [Create a 3 Node Kubernetes Cluster with Minikube][multi_cluster]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[minikube_install]:<https://devopscube.com/minikube-mac/>

[multi_cluster]:<https://medium.com/womenintechnology/create-a-3-node-kubernetes-cluster-with-minikube-8e3dc57d6df2>
