# Compute Engine API

## Enable

```shell
PROJECT_ID=kktyn-dev-1
gcloud compute project-info describe --project ${PROJECT_ID}
API [compute.googleapis.com] not enabled on project [kktyn-dev-1]. Would you like to enable and retry (this will take a few minutes)? (y/N)?
```

# VM instance

## Instance template

#### Public images list

```shell
# Images list
gcloud compute images list
NAME                                           PROJECT              FAMILY                             DEPRECATED  STATUS
debian-11-bullseye-arm64-v20231212             debian-cloud         debian-11-arm64                                READY
debian-11-bullseye-v20231212                   debian-cloud         debian-11                                      READY
```

### Create/Delete instance template

```shell
# Create template

## Global
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
MACHINE_TYPE=e2-small
IMAGE_FAMILY=debian-11
IMAGE_PROJECT=debian-cloud
BOOT_DISK_SIZE=10
BOOT_DISK_TYPE=pd-standard
gcloud compute instance-templates create ${INSTANCE_TEMPLATE_NAME} \
    --machine-type=${MACHINE_TYPE} \
    --image-family=${IMAGE_FAMILY} \
    --image-project=${IMAGE_PROJECT} \
    --boot-disk-size=${BOOT_DISK_SIZE} \
    --boot-disk-type=${BOOT_DISK_TYPE} \
    --quiet

## Region
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
MACHINE_TYPE=e2-small
IMAGE_FAMILY=debian-11
IMAGE_PROJECT=debian-cloud
BOOT_DISK_SIZE=10
BOOT_DISK_TYPE=pd-standard
REGION=europe-west3
gcloud compute instance-templates create ${INSTANCE_TEMPLATE_NAME} \
    --machine-type=${MACHINE_TYPE} \
    --image-family=${IMAGE_FAMILY} \
    --image-project=${IMAGE_PROJECT} \
    --boot-disk-size=${BOOT_DISK_SIZE} \
    --boot-disk-type=${BOOT_DISK_TYPE} \
    --instance-template-region=${REGION} \
    --quiet

# Delete template

## Global
NSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
gcloud compute instance-templates delete ${INSTANCE_TEMPLATE_NAME} \
--quiet

## Region
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
REGION=europe-west3
gcloud compute instance-templates delete ${INSTANCE_TEMPLATE_NAME} \
--region=${REGION} \
--quiet
```

### List/Describe instance templates

```shell
# List
gcloud compute instance-templates list

# Describe

## Global
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
gcloud compute instance-templates describe ${INSTANCE_TEMPLATE_NAME}

## Region
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
REGION=europe-west3
gcloud compute instance-templates describe ${INSTANCE_TEMPLATE_NAME} \
--region=${REGION}
```

## Create/Delete VM instance

1. [Create a VM from an instance template][create_vm_from_instance_template]
   ```shell
   # Create
   VM_NAME=instance-1
   INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
   PROJECT_ID=kktyn-dev-1
   REGION=europe-west3
   ZONE=${REGION}-c
   ENABLE_OS_LOGIN=TRUE
   gcloud compute instances create ${VM_NAME} \
   --source-instance-template projects/${PROJECT_ID}/regions/${REGION}/instanceTemplates/${INSTANCE_TEMPLATE_NAME} \
   --project=${PROJECT_ID} \
   --zone=${ZONE} \
   --metadata enable-oslogin=${ENABLE_OS_LOGIN}
   ```

1. [Create a VM instance from a public image][create_vm_from_image]
   ```shell
   # Create
   VM_NAME=instance-1
   MACHINE_TYPE=e2-small
   IMAGE_FAMILY=debian-11
   IMAGE_PROJECT=debian-cloud
   BOOT_DISK_SIZE=10
   BOOT_DISK_TYPE=pd-standard
   REGION=europe-west3
   ENABLE_OS_LOGIN=TRUE
   gcloud compute instances create ${VM_NAME} \
   --image-family=${IMAGE_FAMILY} \
   --image-project=${IMAGE_PROJECT} \
   --machine-type=${MACHINE_TYPE} \
   --boot-disk-size=${BOOT_DISK_SIZE} \
   --boot-disk-type=${BOOT_DISK_TYPE} \
   --region=${REGION} \
   --metadata enable-oslogin=${ENABLE_OS_LOGIN}
   ```

1. [Create a VM instance from a public image][create_vm_from_image]
   ```shell
   # Detele
   VM_NAME=instance-1
   gcloud compute instances delete ${VM_NAME} \
   --quiet
   ```

# Connect local machine

1. Standard SSH with **[gcload cli][standard_ssh_gcloud]**

   ```shell
   PROJECT_ID=kktyn-dev-1
   ZONE=europe-west3-c
   VM_NAME=instance-1   
   gcloud compute ssh --project=${PROJECT_ID} --zone=${ZONE} ${VM_NAME}
   ```

1. Standard SSH with **[OpenSSH cli][standard_ssh_openssh]**
    1. [Create firewall rule][vpc_network]
    1. [OS Login API - SSH key - Add][os_login]
    1. Read **ExternalIP** address (natIP)
        ```shell
        gcloud compute instances list
        gcloud compute instances list --format="value(networkInterfaces[].accessConfigs[0].natIP)"
        ```
    1. connect ssh from local machine
        ```shell
        ssh -i ~/.ssh/olaszkukutyin ocsko_oliver13_gmail_com@34.107.78.241
        ```

# Related articles

* Google cloud - [Create instance templates][create_instance_template]
* Google cloud - [Create and start a VM instance][create_vm_instance]
    * [Create a VM from an instance template][create_vm_from_instance_template]
    * [Create a VM instance from a public image][create_vm_from_image]
* Google cloud - [Standard SSH][standard_ssh]
    * [gcloud][standard_ssh_gcloud]
    * [OpenSSH][standard_ssh_openssh]
        * [How To SSH into your VM][standard_ssh_openssh_video]

# Back to the [README.MD][readme]

[readme]:<./README.MD>

[create_instance_template]:<https://cloud.google.com/compute/docs/instance-templates/create-instance-templates>

[create_vm_instance]:<https://cloud.google.com/compute/docs/instances/create-start-instance>

[create_vm_from_instance_template]:<https://cloud.google.com/compute/docs/instances/create-vm-from-instance-template>

[create_vm_from_image]:<https://cloud.google.com/compute/docs/instances/create-start-instance#publicimage>

[standard_ssh]:<https://cloud.google.com/compute/docs/connect/standard-ssh>

[standard_ssh_gcloud]:<https://cloud.google.com/compute/docs/connect/standard-ssh#gcloud>

[standard_ssh_openssh]:<https://cloud.google.com/compute/docs/connect/standard-ssh#openssh-client>

[standard_ssh_openssh_video]:<https://www.youtube.com/watch?v=8QGpHQ2SyG8>

[vpc_network]:<./VPC_NETWORK.MD>

[os_login]:<./OS_LOGIN.MD>
