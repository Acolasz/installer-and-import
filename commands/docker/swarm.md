# Usage

## Install

## Commands

### Stack

```shell
docker stack ls
docker stack services <stack_name>
docker stack deploy -c /tmp/docker-compose.yaml <stack_name>
```

### Node

```shell
docker node ls
```

### Service

```shell
# List
docker service ls
```

```shell
docker service ps <service_name>
```

```shell
# Log
docker service logs <service_name> --since 15m
```

```shell
# Restart
docker service update --force <service_name>
```

```shell
# Scaling
docker service update --replicas=0 <service_name>
docker service update --replicas=1 <service_name>
docker service scale <service_name>=0
docker service scale <service_name>=1
```

# Applog


```shell
less /applogs/<service_name>/<service_name>.log
```