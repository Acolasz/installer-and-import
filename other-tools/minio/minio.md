```shell
docker run --rm --name minio-client \
    --env MINIO_SERVER_HOST="localhost:9001" \
    --env MINIO_SERVER_ACCESS_KEY="minio" \
    --env MINIO_SERVER_SECRET_KEY="minio1234" \
    bitnami/minio-client \
    mb minio/my-bucket
```