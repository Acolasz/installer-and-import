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

minikube start --driver qemu --network socket_vmnet
minikube start --addons=ingress --cpus=4 --cni=flannel --install-addons=true --kubernetes-version=stable --memory=8g
```

```shell
minikube service web-service --url

```

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[minikube_install]:<https://devopscube.com/minikube-mac/>
