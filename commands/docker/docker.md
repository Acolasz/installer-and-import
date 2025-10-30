# Usage
kubectl -n monitoring cp prometheus-grafana-6db8b7d854-sm4tw:/var/lib/grafana/grafana.db -c grafana ./grafana.db

## Docker local registry

[with docker container][registry]

## Install

```shell
nohup bash -c "sudo dockerd 2>&1 &"
```

### Docker daemon.json setup

* logrotate
  ```shell
  {
    "log-driver": "json-file",
    "log-opts": {
      "max-size": "250m",
      "max-file": "3"
    }
  }
  ```

### Remote docker daemon setup

```shell
echo "export DOCKER_HOST=tcp://10.120.10.210:2375" >> ~/.bashrc && source ~/.bashrc
```

## Commands

### Build

> [Cross-platform emulator collection distributed with Docker images.][multi_build]

### Login

```shell
docker 
```

### Images

#### Remove images

```shell
# ALL
docker rmi $(docker images --format="{{.ID}}")
# <none> (dangling)
docker rmi $(docker images -f "dangling=true" -q)
docker image prune -a
```

#### Image [load][image_load]/[save][image_save] tar

```shell
docker load --input fedora.tar
docker load < busybox.tar.gz
```

```shell
docker save busybox > busybox.tar
docker save --output busybox.tar busybox
```

#### Tags

```shell
docker pull artifactory.kukutyin.hu/docker/path:latest
docker tag artifactory.kukutyin.hu/docker/path:latest nexus.kukutyin.hu/docker-public/path:latest
docker push nexus.kukutyin.hu/docker-public/path:latest
```

### Run

```shell
docker run --rm -p 7444:80 \
-e PGADMIN_DEFAULT_EMAIL=acolasz@postgres.local \
-e PGADMIN_DEFAULT_PASSWORD=acolasz \
--name pgadmin-1 \
-d dpage/pgadmin4
```

### Container

```shell
docker stats
```

### Formatter

```shell
docker ps --format "{{.ID}} {{.Ports}} {{.Status}} {{.Names}}"
docker images --format="{{.ID}}"
```

### System

```shell
docker system prune --force
```

# Dockerfile instruction

## RUN

```shell
# Create user/group
RUN groupadd --gid ${USER_GID} ${USERNAME} && \
    useradd --no-log-init --system --home /home/${USERNAME} --create-home --uid ${USER_UID} --gid ${USERNAME} --shell /bin/bash ${USERNAME}
```

## COPY

```shell
COPY --chown=${USERNAME}:${USERNAME} --chmod=755 entrypoint.sh /opt/entrypoint.sh
```

# Troubleshooting Linux-on

> **probléma:**
> a docker image készítésekor nem tudunk leszedni linux-os package-ket vagy letölteni csomagolt fájlokat
>
> **ip feloldás:**
> saját linux-unkon az /etc/resolv.conf fájlt kell felülírni

[registry]:<https://docs.docker.com/registry/deploying/>

[image_load]:<https://docs.docker.com/engine/reference/commandline/load/>

[image_save]:<https://docs.docker.com/engine/reference/commandline/save/>

[multi_build]:<https://github.com/tonistiigi/binfmt>
