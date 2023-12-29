# Usage

## Create/Delete folder

> If not exist Organisation (not ID=0), then this can not use.

```shell
ORGANIZATION_ID=0
DISPLAY_NAME=kktyndev
gcloud resource-manager folders create \
--display-name=${DISPLAY_NAME} \
--organization=${ORGANIZATION_ID}
```

## Create/Delete project

```shell
# Create
PROJECT_ID=kktyn-dev-1
gcloud projects create ${PROJECT_ID} \
--set-as-default \
--quiet

# if exists FOLDER_ID
PROJECT_ID=kktyn-dev-1
FOLDER_ID=kktyndev
gcloud projects create ${PROJECT_ID} \
--folder=${FOLDER_ID} \
--set-as-default \
--quiet

# Delete
PROJECT_ID=kktyn-dev-1
gcloud projects delete ${PROJECT_ID} \
--quiet
```

### List/Describe projects

```shell
gcloud projects list

PROJECT_ID=kktyn-dev-1
gcloud projects describe ${PROJECT_ID}
```

### Project info

```shell
PROJECT_ID=kktyn-dev-1
gcloud compute project-info describe --project ${PROJECT_ID}
```

## Region/Zone

### Setup/Remove Region/Zone

```shell
# Setup
gcloud compute project-info add-metadata \
--metadata google-compute-default-region=europe-west3,google-compute-default-zone=europe-west3-c

# Remove
gcloud compute project-info remove-metadata \
--keys=google-compute-default-region,google-compute-default-zone
```

## Added SSH key

1. Added SSH key to Project

   ```shell
   gcloud compute project-info describe \
   --format="value(commonInstanceMetadata[items][ssh-keys])"
   
   # Instance
   SSH_KEY=./ssh-key.txt
   gcloud compute project-info add-metadata \
   --metadata-from-file=ssh-keys=${SSH_KEY}
   ```
1. Added SSH key to VM

   ```shell
   VM_NAME=instance-1
   gcloud compute instances describe ${VM_NAME}
   
   VM_NAME=instance-1
   SSH_KEY_PUB=/Users/user/.ssh/id_rsa.pub
   gcloud compute instances add-metadata ${VM_NAME} --metadata-from-file ssh-keys=${SSH_KEY_PUB}
   ```

# Related articles

* Google cloud - [Add SSH keys to project metadata][added_sshkey_like_metadata]

# Back to the [README.MD][readme]

[readme]:<./README.MD>

[added_sshkey_like_metadata]:<https://cloud.google.com/compute/docs/connect/add-ssh-keys#add_ssh_keys_to_project_metadata>
