# Filestore API

## Enable

```shell
gcloud services enable file.googleapis.com
```

## Create/Delete filestore

### NFS Share

```shell
# Create
FS_NAME=nfs-server-1
PROJECT_ID=kktyn-dev-2
REGION=europe-west2
ZONE=${REGION}-a
gcloud filestore instances create ${FS_NAME} \
--zone=${ZONE} \
--project=${PROJECT_ID} \
--tier=BASIC_HDD \
--file-share=name="nfs_share",capacity=50GB \
--network=name="default" \
--quiet

# Delete
FS_NAME=nfs-server-1
REGION=europe-west2
ZONE=${REGION}-a
gcloud filestore instances delete ${FS_NAME} \
--zone=${ZONE} \
--quiet
```

## List/Describe filestore

```shell
# List
REGION=europe-west2
ZONE=${REGION}-a
gcloud filestore instances list \
--zone=${ZONE}

# Describe
FS_NAME=nfs-server-1
REGION=europe-west2
ZONE=${REGION}-a
gcloud filestore instances describe ${FS_NAME} \
--zone=${ZONE}
```

# Related articles

* [Create NFS share using Google Filestore][nfs_share_filestore]

[nfs_share_filestore]:<https://medium.com/@prayag-sangode/create-nfs-share-using-google-filestore-fc00b58f5731>
