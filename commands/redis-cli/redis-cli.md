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

### SET command

```shell
redis-cli -h <host> -p 6379 -a <password> -c EVAL "return redis.call('SET', KEYS[1], ARGV[1])" 1 my_key1 my_value1
redis-cli -h <host> -p 6379 -a <password> -c EVAL "return redis.call('GET', KEYS[1])" 1 my_key1
# Setup TTL
redis-cli -h <host> -p 6379 -a <password> -c EVAL "return redis.call('EXPIRE', KEYS[1], ARGV[1])" 1 my_key1 20
redis-cli -h <host> -p 6379 -a <password> -c EVAL "redis.call('SET', KEYS[1], ARGV[1]) redis.call('EXPIRE', KEYS[1], ARGV[2])" 1 my_key2 my_value2 60
```

#### Cluster

> FLUSHALL

```shell
redis-cli -h <statefulset_name-0>.<headless-service-name>.<namepsace>.svc.cluster.local -p 6379 -a <password> -c FLUSHALL
redis-cli -h <statefulset_name-1>.<headless-service-name>.<namepsace>.svc.cluster.local -p 6379 -a <password> -c FLUSHALL
redis-cli -h <statefulset_name-2>.<headless-service-name>.<namepsace>.svc.cluster.local -p 6379 -a <password> -c FLUSHALL
```

# Related articles

* [How to Deploy Redis Cluster on Kubernetes (Begginer)][kubernetes_redis]
* [Configuring Redis using a ConfigMap][config_kubernetes_redis]

[kubernetes_redis]:<https://phoenixnap.com/kb/kubernetes-redis>

[config_kubernetes_redis]:<https://kubernetes.io/docs/tutorials/configuration/configure-redis-using-configmap/>
