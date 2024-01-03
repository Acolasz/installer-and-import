# K3s install to GCP - Compute Engine VM instances

## Create VM instance

```shell
# Master
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

# Worker
VM_NAME=k3s-worker
INSTANCE_TEMPLATE_NAME=instance-temp-e2-small-ew3-stand-1
PROJECT_ID=kktyn-dev-2
REGION=europe-west3
ZONE=${REGION}-c
ENABLE_OS_LOGIN=TRUE
TAGS="k3s,k3s-worker"
gcloud compute instances create ${VM_NAME}-1 ${VM_NAME}-2  \
--source-instance-template projects/${PROJECT_ID}/regions/${REGION}/instanceTemplates/${INSTANCE_TEMPLATE_NAME} \
--project=${PROJECT_ID} \
--zone=${ZONE} \
--metadata enable-oslogin=${ENABLE_OS_LOGIN} \
--tags=${TAGS}
```

## Create group in the unmanaged Instance group
```shell
INSTANCE_GROUP_NAME=k3s-group-1
PROJECT_ID=kktyn-dev-2
DESCRIPTION="Unmanaged instance group for k3s instance"
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instance-groups unmanaged create ${INSTANCE_GROUP_NAME} \
 --project=${PROJECT_ID} \
 --description=${DESCRIPTION} \
 --zone=${ZONE}
 
INSTANCE_GROUP_NAME=k3s-group-1
PROJECT_ID=kktyn-dev-2
DESCRIPTION="Unmanaged instance group for k3s instance"
REGION=europe-west3
ZONE=${REGION}-c
NAMED_PORTS=tcp:22,tcp:6443
gcloud compute instance-groups unmanaged set-named-ports ${INSTANCE_GROUP_NAME} \
--project=${PROJECT_ID} \
--zone=${ZONE} \
--named-ports=${NAMED_PORTS}

INSTANCE_GROUP_NAME=k3s-group-1
PROJECT_ID=kktyn-dev-2
DESCRIPTION="Unmanaged instance group for k3s instance"
REGION=europe-west3
ZONE=${REGION}-c
INSTANCE_LIST="k3s-master-1,k3s-worker-1,k3s-worker-2"
gcloud compute instance-groups unmanaged add-instances ${INSTANCE_GROUP_NAME} \
--project=${PROJECT_ID} \
--zone=${ZONE} \
--instances=${INSTANCE_LIST}

# List
INSTANCE_GROUP_NAME=k3s-group-1
REGION=europe-west3
ZONE=${REGION}-c
gcloud compute instance-groups unmanaged list-instances ${INSTANCE_GROUP_NAME} \
--zone=${ZONE}
```

## Create Firewall rules

```shell
# SSH
FIREWALL_RULES_NAME=ssh-from-my-ip
PROJECT_ID=kktyn-dev-2
NETWORK=default
RULES=tcp:22
IP_ADDRESS=46.35.192.188/32
gcloud compute firewall-rules create ${FIREWALL_RULES_NAME} \
--description="Allow ssh from my home IP Address" \
--project=${PROJECT_ID} \
--direction=INGRESS \
--priority=1000 \
--network=${NETWORK} \
--action=ALLOW \
--rules=${RULES} \
--source-ranges=${IP_ADDRESS}
# KUBECONFIG
FIREWALL_RULES_NAME=kube-from-my-ip
PROJECT_ID=kktyn-dev-2
NETWORK=default
RULES=tcp:6443
IP_ADDRESS=0.0.0.0/0
TAGS="k3s"
gcloud compute firewall-rules create ${FIREWALL_RULES_NAME} \
--description="Allow kubeconfig from my home IP Address" \
--project=${PROJECT_ID} \
--direction=INGRESS \
--priority=900 \
--network=${NETWORK} \
--action=ALLOW \
--rules=${RULES} \
--source-ranges=${IP_ADDRESS} \
--target-tags=${TAGS}
```

## Install

### master node

```shell
gcloud compute instances list \
--filter=tags.items=k3s-master \
--format="get(networkInterfaces[0].accessConfigs.natIP)"

PRIMARY_SERVER_IP=34.159.169.111
SSH_KEY_PRIVATE=/Users/user/.ssh/olaszkukutyin
ACCOUNT_USER=ocsko_oliver13_gmail_com
k3sup install --ip ${PRIMARY_SERVER_IP} --context k3s \
--ssh-key ${SSH_KEY_PRIVATE} \
--user ${ACCOUNT_USER}
```

### worker node

```shell
gcloud compute instances list \
--filter=tags.items=k3s-worker \
--format="get(networkInterfaces[0].accessConfigs.natIP)"

AGENT_SERVER_IP=34.159.190.146

AGENT_SERVER_IP=34.107.103.0
PRIMARY_SERVER_IP=34.159.169.111
SSH_KEY_PRIVATE=/Users/user/.ssh/olaszkukutyin
ACCOUNT_USER=ocsko_oliver13_gmail_com
k3sup join --ip ${AGENT_SERVER_IP} \
--server-ip ${PRIMARY_SERVER_IP} \
--ssh-key ${SSH_KEY_PRIVATE} \
--user ${ACCOUNT_USER} \
--node-label worker
# OR
kubectl get nodes
kubectl label node k3s-worker-1 node-role.kubernetes.io/worker=worker
```

# Update VM instance ExternalIP

```shell
NEW_PRIMARY_SERVER_IP=34.159.190.146
ACCOUNT_USER=ocsko_oliver13_gmail_com
SSH_KEY_PRIVATE=/Users/user/.ssh/olaszkukutyin
k3sup install \
--ip ${NEW_PRIMARY_SERVER_IP} \
--user ${ACCOUNT_USER} \
--ssh-key ${SSH_KEY_PRIVATE} \
--cluster
#
NEW_PRIMARY_SERVER_IP=$(gcloud compute instances list --filter=tags.items=k3s-master --format="get(networkInterfaces[0].accessConfigs.natIP)")
ACCOUNT_USER=ocsko_oliver13_gmail_com
SSH_KEY_PRIVATE=/Users/user/.ssh/olaszkukutyin
k3sup install \
--ip ${NEW_PRIMARY_SERVER_IP} \
--user ${ACCOUNT_USER} \
--ssh-key ${SSH_KEY_PRIVATE} \
--cluster
```

```shell
KUBECONFIG=~/.kube/config-kktyn-dev-2-k3s k get nodes
```

# Related articles

* [Trying tiny k3s on google cloud with k3sup][k3s_install_gcp_vm]
* [Provision K3s On Google Cloud With Terraform And K3sup][k3s_install_gcp_vm]

[k3s_install_gcp_vm]:<https://www.starkandwayne.com/blog/trying-tiny-k3s-on-google-cloud-with-k3sup/index.html>

[k3s_install_gcp_vm2]:<https://nimblehq.co/blog/provision-k3s-on-google-cloud-with-terraform-and-k3sup>
