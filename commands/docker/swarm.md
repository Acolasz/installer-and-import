# Usage

## Install

## Commands

```shell
# List
docker service ls
docker service logs <service_name> --since 15m
docker service logs eets_efccontextdata --since 15m
```

```shell
# Restart
docker service update --force <service_name>
```

```shell
# Scaling
docker service update --replicas=0 eets_efccontextdata
docker service update --replicas=1 eets_efccontextdata
docker service scale eets_efccontextdata=0
docker service scale eets_efccontextdata=1
```
