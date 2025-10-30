# Install

```shell
kubectl run redis-cli --rm -i --tty --image bitnami/redis:7.4.2-debian-12-r2 -n redis-sentinel -- bash
```

# Usage
```shell
redis-benchmark -h redis-bitnami-cluster-redis-cluster-headless -p 6379 -a cluster -k 1 -P 10 -n 100000 -c 50 --cluster
redis-benchmark -h redis-bitnami-cluster-redis-cluster-headless -p 6379 -a cluster -k 1 -P 10 -n 100000 -c 50 --cluster -t set,get,incr

# check nodes
redis-cli -h redis-bitnami-cluster-redis-cluster-0.redis-bitnami-cluster-redis-cluster-headless.redis-sentinel.svc.cluster.local -p 6379 -a cluster 'KEYS' '*'
redis-cli -h redis-bitnami-cluster-redis-cluster-1.redis-bitnami-cluster-redis-cluster-headless.redis-sentinel.svc.cluster.local -p 6379 -a cluster 'KEYS' '*'
redis-cli -h redis-bitnami-cluster-redis-cluster-2.redis-bitnami-cluster-redis-cluster-headless.redis-sentinel.svc.cluster.local -p 6379 -a cluster 'KEYS' '*'

redis-cli -h redis-bitnami-cluster-redis-cluster-0.redis-bitnami-cluster-redis-cluster-headless.redis-sentinel.svc.cluster.local -p 6379 -a cluster -c FLUSHALL
redis-cli -h redis-bitnami-cluster-redis-cluster-1.redis-bitnami-cluster-redis-cluster-headless.redis-sentinel.svc.cluster.local -p 6379 -a cluster -c FLUSHALL
redis-cli -h redis-bitnami-cluster-redis-cluster-2.redis-bitnami-cluster-redis-cluster-headless.redis-sentinel.svc.cluster.local -p 6379 -a cluster -c FLUSHALL


redis-cli -h redis-bitnami-cluster-redis-cluster-headless -p 6379 -a cluster script load "return redis.call('SET', KEYS[1], ARGV[1])"
redis-benchmark -h redis-bitnami-cluster-redis-cluster-headless -p 6379 -a cluster -n 10000 -q evalsha "d8f2fad9f8e86a53d2a6ebd960b33c4972cacc37" 1 mykey myvalue
```