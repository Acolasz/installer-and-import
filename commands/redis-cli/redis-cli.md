# Redis CLI

## Connect

```shell
redis-cli -h redis-auth-master.dev-redis -a password
# in container
kubectl exec -it pods/redis-master-0 -n redis -- sh
redis-cli -h localhost -a password
```

## redis-cli commands

```shell
PING
CONFIG GET maxmemory
CONFIG GET maxmemory-policy
```

# Related articles

* [How to Deploy Redis Cluster on Kubernetes (Begginer)][kubernetes_redis]
* [Configuring Redis using a ConfigMap][config_kubernetes_redis]

[kubernetes_redis]:<https://phoenixnap.com/kb/kubernetes-redis>

[config_kubernetes_redis]:<https://kubernetes.io/docs/tutorials/configuration/configure-redis-using-configmap/>
