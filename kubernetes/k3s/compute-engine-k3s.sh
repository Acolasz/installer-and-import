#!/usr/bin/env bash

REGION_EW1="europe-west1"
REGION_EW2="europe-west2"
REGION_EW3="europe-west3"
ZONE_EW1_A="${REGION_EW1}-a"
ZONE_EW1_B="${REGION_EW1}-b"
ZONE_EW1_C="${REGION_EW1}-c"
ZONE_EW2_A="${REGION_EW2}-a"
ZONE_EW2_B="${REGION_EW2}-b"
ZONE_EW2_C="${REGION_EW2}-c"
ZONE_EW3_A="${REGION_EW3}-a"
ZONE_EW3_B="${REGION_EW3}-b"
ZONE_EW3_C="${REGION_EW3}-c"

delete-instance-k3s() {
  local L_ZONE=$1
  local L_TAGS=${2:-k3s}
  gcloud compute instances delete $(gcloud compute instances list --filter=tags.items=k3s --format="get(name)") \
  --zone=${ZONE} \
  --quiet
}


delete-instance-k3s-all() {
  delete-instance-k3s ${ZONE_EW1_A}
  delete-instance-k3s ${ZONE_EW1_B}
  delete-instance-k3s ${ZONE_EW1_C}
  delete-instance-k3s ${ZONE_EW2_A}
  delete-instance-k3s ${ZONE_EW2_B}
  delete-instance-k3s ${ZONE_EW2_C}
  delete-instance-k3s ${ZONE_EW3_A}
  delete-instance-k3s ${ZONE_EW3_B}
  delete-instance-k3s ${ZONE_EW3_C}
}

list() {
  REGIONS="${REGION_EW1},${REGION_EW2},${REGION_EW3}"
  gcloud compute addresses list --regions=${REGIONS}
}

create-instance-k3s-all() {
  create-instance-k3s "k3s-master-1" "instance-temp-e2-small-ew3-stand-1" "kktyn-dev-2" "${REGION_EW1}" "${REGION_EW1}-a" "startup-script.sh" "k3s,k3s-master"
  create-instance-k3s "k3s-worker-ew1a-1" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW1}" "${REGION_EW1}-a" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew1a-2" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW1}" "${REGION_EW1}-a" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew1b-1" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW1}" "${REGION_EW1}-b" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew1b-2" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW1}" "${REGION_EW1}-b" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew2a-1" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW2}" "${REGION_EW2}-a" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew2a-2" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW2}" "${REGION_EW2}-a" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew2b-1" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW2}" "${REGION_EW2}-b" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew2b-2" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW2}" "${REGION_EW2}-b" "startup-script.sh" "k3s,k3s-worker"
  create-instance-k3s "k3s-worker-ew3c-1" "instance-temp-e2-medium-ew3-stand-30gb" "kktyn-dev-2" "${REGION_EW3}" "${REGION_EW3}-c" "startup-script-nfs-server.sh" "nfs-server"
}

create-instance-k3s() {
  local L_VM_NAME=$1
  local L_INSTANCE_TEMPLATE_NAME=$2
  local L_PROJECT_ID=$3
  local L_REGION=$4
  local L_ZONE=$5
  local L_STARTUP_SCRIPT=./gce/$6
  local L_TAGS=$7
  local L_ENABLE_OS_LOGIN=TRUE
  gcloud compute instances create ${L_VM_NAME} \
  --source-instance-template projects/${L_PROJECT_ID}/regions/${L_REGION}/instanceTemplates/${L_INSTANCE_TEMPLATE_NAME} \
  --project=${L_PROJECT_ID} \
  --zone=${L_ZONE} \
  --metadata enable-oslogin=${L_ENABLE_OS_LOGIN} \
  --metadata-from-file=startup-script=${L_STARTUP_SCRIPT} \
  --tags=${L_TAGS}
}

create-instance-template-k3s() {
  local L_INSTANCE_TEMPLATE_NAME=$1
  local L_PROJECT_ID=$2
  local L_REGION=$3
  local L_MACHINE_TYPE=e2-medium
  local L_IMAGE_FAMILY=debian-11
  local L_IMAGE_PROJECT=debian-cloud
  local L_BOOT_DISK_SIZE=30
  local L_BOOT_DISK_TYPE=pd-standard
  local L_REGION=europe-west3
  gcloud compute instance-templates create ${L_INSTANCE_TEMPLATE_NAME} \
  --machine-type=${L_MACHINE_TYPE} \
  --image-family=${L_IMAGE_FAMILY} \
  --image-project=${L_IMAGE_PROJECT} \
  --boot-disk-size=${L_BOOT_DISK_SIZE} \
  --boot-disk-type=${L_BOOT_DISK_TYPE} \
  --instance-template-region=${L_REGION} \
  --quiet
}

setup-ip-address() {
  local L_VM_NAME=k3s-master-1
  REGION=europe-west3
  ZONE=${REGION}-c
  ACCESS_CONFIG_NAME="external-nat"
  gcloud compute instances delete-access-config ${VM_NAME} \
  --access-config-name=${ACCESS_CONFIG_NAME} \
  --zone=${ZONE}
}

gcloud compute instances describe k3s-master-1

REGION_EW3=europe-west3
ZONE_EW3=${REGION}-c
REGION_EW2=europe-west2
ZONE_EW2=${REGION}-c
ZONES="${ZONE_EW3},${ZONE_EW2}"
gcloud compute instances start $(gcloud compute instances list --filter=tags.items=k3s --format="get(name)" --zones=${ZONES}) \
--zone=${ZONE}

REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instances list --filter=tags.items=k3s --format="get(name)" --zones=${ZONE}


VM_NAME=k3s-master-1
REGION=europe-west3
ZONE=${REGION}-c
ACCESS_CONFIG_NAME="external-nat"
gcloud compute instances delete-access-config ${VM_NAME} \
--access-config-name=${ACCESS_CONFIG_NAME} \
--zone=${ZONE}
VM_NAME=k3s-worker-1
REGION=europe-west3
ZONE=${REGION}-c
ACCESS_CONFIG_NAME="external-nat"
gcloud compute instances delete-access-config ${VM_NAME} \
--access-config-name=${ACCESS_CONFIG_NAME} \
--zone=${ZONE}
VM_NAME=k3s-infra-1
REGION=europe-west3
ZONE=${REGION}-c
ACCESS_CONFIG_NAME="external-nat"
gcloud compute instances delete-access-config ${VM_NAME} \
--access-config-name=${ACCESS_CONFIG_NAME} \
--zone=${ZONE}
VM_NAME=k3s-infra-2
REGION=europe-west3
ZONE=${REGION}-c
ACCESS_CONFIG_NAME="external-nat"
gcloud compute instances delete-access-config ${VM_NAME} \
--access-config-name=${ACCESS_CONFIG_NAME} \
--zone=${ZONE}
VM_NAME=nfs-server-1
REGION=europe-west2
ZONE=${REGION}-a
ACCESS_CONFIG_NAME="external-nat"
gcloud compute instances delete-access-config ${VM_NAME} \
--access-config-name=${ACCESS_CONFIG_NAME} \
--zone=${ZONE}

VM_NAME=k3s-master-1
REGION=europe-west3
ZONE=${REGION}-c
IP_ADDRESS=$(gcloud compute addresses describe ${VM_NAME} --region=${REGION} --format="get(address)")
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}
VM_NAME=k3s-worker-1
REGION=europe-west3
ZONE=${REGION}-c
IP_ADDRESS=$(gcloud compute addresses describe ${VM_NAME} --region=${REGION} --format="get(address)")
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}
VM_NAME=k3s-infra-1
REGION=europe-west3
ZONE=${REGION}-c
IP_ADDRESS=$(gcloud compute addresses describe ${VM_NAME} --region=${REGION} --format="get(address)")
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}
VM_NAME=k3s-infra-2
REGION=europe-west3
ZONE=${REGION}-c
IP_ADDRESS=$(gcloud compute addresses describe ${VM_NAME} --region=${REGION} --format="get(address)")
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}
VM_NAME=nfs-server-1
REGION=europe-west2
ZONE=${REGION}-a
IP_ADDRESS=$(gcloud compute addresses describe ${VM_NAME} --region=${REGION} --format="get(address)")
gcloud compute instances add-access-config ${VM_NAME} \
--address=${IP_ADDRESS} \
--zone=${ZONE}

SCHEDULE_NAME=always-stop-k3s-vms-ew2
SCHEDULE_DESCRIPTION='Always stop VMs 22:30 '
REGION=europe-west2
STOP_OPERATION_SCHEDULE='30 22 * * *'
TIME_ZONE='Europe/Budapest'
INITIATION_DATE='2024-01-23T23:59:00'
gcloud compute resource-policies create instance-schedule ${SCHEDULE_NAME} \
--description=${SCHEDULE_DESCRIPTION} \
--region=${REGION} \
--vm-stop-schedule=${STOP_OPERATION_SCHEDULE} \
--timezone=${TIME_ZONE} \
--initiation-date=${INITIATION_DATE}

SCHEDULE_NAME=always-stop-k3s-vms-ew3
SCHEDULE_DESCRIPTION='Always stop VMs 22:30 '
REGION=europe-west3
STOP_OPERATION_SCHEDULE='30 22 * * *'
TIME_ZONE='Europe/Budapest'
INITIATION_DATE='2024-01-23T23:59:00'
gcloud compute resource-policies create instance-schedule ${SCHEDULE_NAME} \
--description=${SCHEDULE_DESCRIPTION} \
--region=${REGION} \
--vm-stop-schedule=${STOP_OPERATION_SCHEDULE} \
--timezone=${TIME_ZONE} \
--initiation-date=${INITIATION_DATE}

VM_NAME=k3s-master-1
SCHEDULE_NAME=always-stop-k3s-vms-ew3
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instances add-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME} \
--zone=${ZONE}

VM_NAME=k3s-worker-1
SCHEDULE_NAME=always-stop-k3s-vms-ew3
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instances add-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME} \
--zone=${ZONE}

VM_NAME=k3s-infra-1
SCHEDULE_NAME=always-stop-k3s-vms-ew3
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instances add-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME} \
--zone=${ZONE}

VM_NAME=k3s-infra-2
SCHEDULE_NAME=always-stop-k3s-vms-ew3
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instances add-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME} \
--zone=${ZONE}

VM_NAME=nfs-server-1
SCHEDULE_NAME=always-stop-k3s-vms-ew2
REGION=europe-west2
ZONE=${REGION}-a
gcloud compute instances add-resource-policies ${VM_NAME} \
--resource-policies=${SCHEDULE_NAME} \
--zone=${ZONE}

gcloud compute instances list

PRIMARY_SERVER_IP=$(gcloud compute instances list --filter=tags.items=k3s-master --format="get(networkInterfaces[0].accessConfigs.natIP)")
SSH_KEY_PRIVATE=/Users/oliver.ocsko/.ssh/olaszkukutyin
ACCOUNT_USER=ocsko_oliver13_gmail_com
k3sup install --ip ${PRIMARY_SERVER_IP} --context k3s \
--ssh-key ${SSH_KEY_PRIVATE} \
--user ${ACCOUNT_USER}

REGION=europe-west3
ZONE=${REGION}-c
AGENT_SERVER_IP=$(gcloud compute instances describe k3s-infra-1 --zone=${ZONE} --format="get(networkInterfaces[0].accessConfigs.natIP)")
PRIMARY_SERVER_IP=$(gcloud compute instances list --filter=tags.items=k3s-master --format="get(networkInterfaces[0].accessConfigs.natIP)")
SSH_KEY_PRIVATE=/Users/oliver.ocsko/.ssh/olaszkukutyin
ACCOUNT_USER=ocsko_oliver13_gmail_com
k3sup join --ip ${AGENT_SERVER_IP} \
--server-ip ${PRIMARY_SERVER_IP} \
--ssh-key ${SSH_KEY_PRIVATE} \
--user ${ACCOUNT_USER}

REGION=europe-west3
ZONE=${REGION}-c
AGENT_SERVER_IP=$(gcloud compute instances describe k3s-infra-2 --zone=${ZONE} --format="get(networkInterfaces[0].accessConfigs.natIP)")
PRIMARY_SERVER_IP=$(gcloud compute instances list --filter=tags.items=k3s-master --format="get(networkInterfaces[0].accessConfigs.natIP)")
SSH_KEY_PRIVATE=/Users/oliver.ocsko/.ssh/olaszkukutyin
ACCOUNT_USER=ocsko_oliver13_gmail_com
k3sup join --ip ${AGENT_SERVER_IP} \
--server-ip ${PRIMARY_SERVER_IP} \
--ssh-key ${SSH_KEY_PRIVATE} \
--user ${ACCOUNT_USER}

REGION=europe-west3
ZONE=${REGION}-c
AGENT_SERVER_IP=$(gcloud compute instances describe k3s-worker-1 --zone=${ZONE} --format="get(networkInterfaces[0].accessConfigs.natIP)")
PRIMARY_SERVER_IP=$(gcloud compute instances list --filter=tags.items=k3s-master --format="get(networkInterfaces[0].accessConfigs.natIP)")
SSH_KEY_PRIVATE=/Users/oliver.ocsko/.ssh/olaszkukutyin
ACCOUNT_USER=ocsko_oliver13_gmail_com
k3sup join --ip ${AGENT_SERVER_IP} \
--server-ip ${PRIMARY_SERVER_IP} \
--ssh-key ${SSH_KEY_PRIVATE} \
--user ${ACCOUNT_USER}

cp ./kubeconfig ~/.kube/config-kktyn-dev-2-k3s

ansible-playbook -i ./inventory/gcp-k3s-inventory.yaml ./playbook/longhorn.yml -e HOSTS_NAME=WORKER_ALL

kubectl get nodes

kubectl taint nodes k3s-master-1 role=master:NoSchedule

kubectl label node k3s-worker-1 node-role.kubernetes.io/worker=worker
kubectl label node k3s-infra-1 node-role.kubernetes.io/infra=infra
kubectl label node k3s-infra-2 node-role.kubernetes.io/infra=infra

kubectl apply -f ./longhorn/preinstall/longhorn-iscsi-installation-1.5.3.yaml

kubectl taint nodes k3s-infra-1 infra-
kubectl taint nodes k3s-infra-2 infra-
kubectl taint nodes k3s-infra-1 infra:NoSchedule
kubectl taint nodes k3s-infra-2 infra:NoSchedule

kubectl get nodes -o=custom-columns="NodeName:.metadata.name,TaintKey:.spec.taints[*].key,TaintValue:.spec.taints[*].value,TaintEffect:.spec.taints[*].effect"

kubectl get nodes

ssh -i ~/.ssh/olaszkukutyin ocsko_oliver13_gmail_com@$(gcloud compute instances list --filter=tags.items=nfs-server --format="get(networkInterfaces[0].accessConfigs.natIP)")
ssh -i ~/.ssh/olaszkukutyin ocsko_oliver13_gmail_com@$(gcloud compute instances describe k3s-infra-1 --format="get(networkInterfaces[0].accessConfigs.natIP)")
ssh -i ~/.ssh/olaszkukutyin ocsko_oliver13_gmail_com@$(gcloud compute instances describe k3s-infra-2 --format="get(networkInterfaces[0].accessConfigs.natIP)")
ssh -i ~/.ssh/olaszkukutyin ocsko_oliver13_gmail_com@$(gcloud compute instances describe k3s-worker-1 --format="get(networkInterfaces[0].accessConfigs.natIP)")

sudo mount -t nfs -o resvport 34.105.156.186:/opt/nfs /Users/user/workspaces/github/acolasz/installer-and-import/GOOGLECLOUD/nfsshare

exit 0
