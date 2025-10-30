# Usage

## MacOS minio cli

> Documentation: [minio-mc][minio_mc]

```shell
brew install minio/stable/mc
mc --version
```

### Connect to minio server

```shell
mc alias set local http://127.0.0.1:9000 {MINIO_ROOT_USER} {MINIO_ROOT_PASSWORD}
mc admin info local
```

## Docker minio cli

```shell
docker pull minio/mc
docker run minio/mc ls play
```

# Related articles

* [minio/mc][docker_run_mc]

[minio_mc]:<https://min.io/docs/minio/linux/reference/minio-mc.html>

[docker_run_mc]:<https://github.com/minio/mc>