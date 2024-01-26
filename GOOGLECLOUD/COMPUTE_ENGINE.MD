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
# e2-medium
INSTANCE_TEMPLATE_NAME=instance-temp-e2-medium-ew3-stand-30gb
MACHINE_TYPE=e2-medium
IMAGE_FAMILY=debian-11
IMAGE_PROJECT=debian-cloud
BOOT_DISK_SIZE=30
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
# e2-medium other region
INSTANCE_TEMPLATE_NAME=instance-temp-e2-medium-ew2-stand-30gb
MACHINE_TYPE=e2-medium
IMAGE_FAMILY=debian-11
IMAGE_PROJECT=debian-cloud
BOOT_DISK_SIZE=30
BOOT_DISK_TYPE=pd-standard
REGION=europe-west2
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
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-2
gcloud compute instance-templates delete ${INSTANCE_TEMPLATE_NAME} \
--quiet

## Region
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-2
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
INSTANCE_TEMPLATE_NAME=instance-temp-e2-medium-ew3-stand-30gb
REGION=europe-west3
gcloud compute instance-templates describe ${INSTANCE_TEMPLATE_NAME} \
--region=${REGION}
```

## Create/Delete VM instance

1. [Create a VM from an instance template][create_vm_from_instance_template]
   ```shell
   # Create
   VM_NAME=k3s-master-1
   INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
   PROJECT_ID=kktyn-dev-2
   REGION=europe-west3
   ZONE=${REGION}-c
   ENABLE_OS_LOGIN=TRUE
   TAGS="k3s,k3s-master"
   gcloud compute instances create ${VM_NAME} \
   --source-instance-template projects/${PROJECT_ID}/regions/${REGION}/instanceTemplates/${INSTANCE_TEMPLATE_NAME} \
   --project=${PROJECT_ID} \
   --zone=${ZONE} \
   --metadata enable-oslogin=${ENABLE_OS_LOGIN} \
   --tags=${TAGS}
   # Create startup-script
   VM_NAME=k3s-infra-2
   INSTANCE_TEMPLATE_NAME=instance-temp-e2-medium-ew3-stand-30gb
   PROJECT_ID=kktyn-dev-2
   REGION=europe-west3
   ZONE=${REGION}-c
   ENABLE_OS_LOGIN=TRUE
   STARTUP_SCRIPT=./gce/startup-script.sh
   TAGS="k3s,k3s-infra"
   gcloud compute instances create ${VM_NAME} \
   --source-instance-template projects/${PROJECT_ID}/regions/${REGION}/instanceTemplates/${INSTANCE_TEMPLATE_NAME} \
   --project=${PROJECT_ID} \
   --zone=${ZONE} \
   --metadata enable-oslogin=${ENABLE_OS_LOGIN} \
   --metadata-from-file=startup-script=${STARTUP_SCRIPT} \
   --tags=${TAGS}
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

1. [Delete a VM instance from a public image][create_vm_from_image]
   ```shell
   # Detele
   VM_NAME=instance-1
   gcloud compute instances delete ${VM_NAME} \
   --quiet
   ## Zone
   VM_NAME=instance-1
   ZONE=europe-west3-c
   gcloud compute instances delete ${VM_NAME} \
   --zone=${ZONE} \
   --quiet
   ```

## List/Describe VM instance

```shell
# List
gcloud compute instances list
## Format
gcloud compute instances list --format="value(networkInterfaces[].accessConfigs[0].natIP)"
gcloud compute instances list --format="table(
  name:label=VM_Name,
  networkInterfaces[].accessConfigs[0].natIP:label=ExternalIP,
  tags[].items[].notnull().list():label=Tags
)"
## Filter
gcloud compute instances list \
--filter=tags.items=k3s-master \
--format="get(networkInterfaces[0].accessConfigs.natIP)"

# Describe
ZONE=europe-west3-c
VM_NAME=instance-1 
gcloud compute instances describe ${VM_NAME} \
--zone=${ZONE}
```

## Start/Stop instance

```shell
# Start
VM_NAME=k3s-master-1
ZONE=europe-west3-c
gcloud compute instances start ${VM_NAME} \
--zone=${ZONE}
# Multi start
ZONE=europe-west3-c
gcloud compute instances start $(gcloud compute instances list --filter=tags.items=k3s --format="get(name)") \
--zone=${ZONE}

# Stop
VM_NAME=k3s-master-1
ZONE=europe-west3-c
gcloud compute instances stop ${VM_NAME} \
--zone=${ZONE}
# Multi stop
ZONE=europe-west3-c
gcloud compute instances stop $(gcloud compute instances list --filter=tags.items=k3s --format="get(name)") \
--zone=${ZONE}
```

## Start/Stop instances with schedule instance

### Create/Delete schedule instance

```shell
# Create
SCHEDULE_NAME=daily-stop-k3s-vms2
SCHEDULE_DESCRIPTION='Daily stop VMs 21:59 and start 08:29'
REGION=europe-west3
STOP_OPERATION_SCHEDULE='30 22 * * *'
START_OPERATION_SCHEDULE='29 8 * * *'
TIME_ZONE='Europe/Budapest'
INITIATION_DATE='2024-01-03T23:59:00'
gcloud compute resource-policies create instance-schedule ${SCHEDULE_NAME} \
--description=${SCHEDULE_DESCRIPTION} \
--region=${REGION} \
--vm-stop-schedule=${STOP_OPERATION_SCHEDULE} \
--vm-start-schedule=${START_OPERATION_SCHEDULE} \
--timezone=${TIME_ZONE} \
--initiation-date=${INITIATION_DATE}

# Delete
## if is already being used by instance, then from the instance have to remove it.
SCHEDULE_NAME=always-stop-k3s-vms
REGION=europe-west3
gcloud compute resource-policies delete ${SCHEDULE_NAME} \
--region=${REGION}
```

### List/describe schedule instance

```shell
# List
gcloud compute resource-policies list

# Describe
SCHEDULE_NAME=daily-stop-k3s
REGION=europe-west3
gcloud compute resource-policies describe ${SCHEDULE_NAME} \
--region=${REGION}
```

## Update (Add/Remove) instances 

### with schedule instance

```shell
# Add resource-policy
VM_NAME=k3s-master-1
SCHEDULE_NAME=daily-stop-k3s-vms
gcloud compute instances add-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME}

# Remove resource-policy
VM_NAME=k3s-infra-1
SCHEDULE_NAME=always-stop-k3s-vms
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instances remove-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME} \
--zone=${ZONE}
```

### with network interface (accessConfig)

```shell
# Delete access-config
VM_NAME=k3s-master-1
REGION=europe-west3
ZONE=${REGION}-c
ACCESS_CONFIG_NAME="External NAT"
gcloud compute instances delete-access-config ${VM_NAME} \
--access-config-name=${ACCESS_CONFIG_NAME} \
--zone=${ZONE}

# Add access-config
VM_NAME=k3s-infra-1
REGION=europe-west3
ZONE=${REGION}-c
IP_ADDRESS=34.159.176.157
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}

VM_NAME=k3s-master-1
REGION=europe-west3
ZONE=${REGION}-c
IP_ADDRESS=$(gcloud compute addresses describe ${VM_NAME} --region=${REGION} --format="get(address)")
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}
```

# IP Address

## Create/Delete IP Address

```shell
# Delete
VM_ADDRESS_NAME_1=external-static-ip	
VM_ADDRESS_NAME_2=external-static-ip
VM_ADDRESS_NAMES="${VM_ADDRESS_NAME_1},${VM_ADDRESS_NAME_2}"
REGION=europe-west3
gcloud compute addresses delete ${VM_ADDRESS_NAMES} \
--project=${PROJECT_ID} \
--region=${REGION} \
--quiet

# Create
VM_ADDRESS_NAME=k3s-master-1
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute addresses create ${VM_ADDRESS_NAME} \
--project=${PROJECT_ID} \
--region=${REGION}
```

## List/Describe 

```shell
# List
REGION_EW2=europe-west2
REGION_EW3=europe-west3
REGIONS="${REGION_EW2},${REGION_EW3}"
gcloud compute addresses list \
--regions=${REGIONS}

# Describe
VM_ADDRESS_NAME=nfs-server-1
REGION_EW2=europe-west2
gcloud compute addresses describe ${VM_ADDRESS_NAME} \
--region=${REGION_EW2} 
## Formatter
VM_ADDRESS_NAME=nfs-server-1
REGION_EW2=europe-west2
gcloud compute addresses describe ${VM_ADDRESS_NAME} \
--region=${REGION_EW2} \
--format="get(address)"
```

# Unmanaged instance group

```shell
# List
gcloud compute instance-groups unmanaged list
# Describe
VM_GRUOP_NAME=instance-group-1
ZONE=europe-west3-c
gcloud compute instance-groups unmanaged describe ${VM_GRUOP_NAME} \
--zone=${ZONE}
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
        ```
        ```shell
        gcloud compute instances list --format="value(networkInterfaces[].accessConfigs[0].natIP)"
        ```
    1. connect ssh from local machine
        ```shell
        ssh -i ~/.ssh/olaszkukutyin ocsko_oliver13_gmail_com@34.159.169.111
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
* [NFS Filestore on GCP for free][nfs_server]

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

[nfs_server]:<https://medium.com/@ngoodger_7766/nfs-filestore-on-gcp-for-free-859593e18bdf>
