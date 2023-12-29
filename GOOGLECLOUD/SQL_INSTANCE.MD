# Commands

## SQL instances

```shell
gcloud sql instances list
```

### Special cmd

```shell
gcloud sql instances list --limit=NUM
gcloud sql instances list --limit=1
NAME               DATABASE_VERSION  LOCATION           TIER              PRIMARY_ADDRESS  PRIVATE_ADDRESS  STATUS
postgres-1-ms0nm8  POSTGRES_14       asia-southeast1-a  db-custom-2-8192  34.0.0.0         10.0.0.0         RUNNABLE
postgres-2-ms0nm8  POSTGRES_14       asia-southeast1-a  db-custom-2-8192  34.0.0.1         10.0.0.1         RUNNABLE

# ASC
gcloud sql instances list --limit=10 --sort-by=NAME
# DESC
gcloud sql instances list --limit=10 --sort-by=NAME~

# grouping with PAGE_SIZE
gcloud sql instances list --sort-by=NAME~ --page-size=NUM
gcloud sql instances list --sort-by=NAME~ --page-size=2

# filter
gcloud sql instances list --sort-by=NAME~ --filter=STRING
gcloud sql instances list --sort-by=NAME~ --filter=postgres
```

#### get URI

```shell
gcloud sql instances list --uri

> https://sqladmin.googleapis.com/sql/v1beta4/projects/infra-s-1/instances/infra-s-1-user-8h5k
```

# Related articles

# Back to the [README.MD][readme]

[readme]:<./README.MD>
