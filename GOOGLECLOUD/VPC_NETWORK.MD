# VPC Network

## Cloud Firewall policies

### Firewall policies

#### VPC firewall rules

##### Create/Delete firewall rule

1. Create
```shell
FIREWALL_RULES_NAME=allow-ssh-from-my-home-ip
PROJECT_ID=kktyn-dev-2
NETWORK=default
RULES=tcp:22
IP_ADDRESS=0.0.0.0/0
gcloud compute firewall-rules create ${FIREWALL_RULES_NAME} \
--description="Allow ssh from my home IP Address" \
--project=${PROJECT_ID} \
--direction=INGRESS \
--priority=1000 \
--network=${NETWORK} \
--action=ALLOW \
--rules=${RULES} \
--source-ranges=${IP_ADDRESS}
```

```shell
FIREWALL_RULES_NAME=k3s
PROJECT_ID=kktyn-dev-2
NETWORK=default
RULES=tcp:6443
IP_ADDRESS=0.0.0.0/0
TAGS="k3s"
gcloud compute firewall-rules create ${FIREWALL_RULES_NAME} \
--description="Allow k3s from my home IP Address" \
--project=${PROJECT_ID} \
--direction=INGRESS \
--priority=900 \
--network=${NETWORK} \
--action=ALLOW \
--rules=${RULES} \
--source-ranges=${IP_ADDRESS} \
--target-tags=${TAGS}
```

```shell
FIREWALL_RULES_NAME=allow-test-from-my-home-ip
PROJECT_ID=kktyn-dev-1
NETWORK=default
RULES=tcp:6443
IP_ADDRESS=0.0.0.0/0
gcloud compute firewall-rules create ${FIREWALL_RULES_NAME} \
--description="Allow test from my home IP Address" \
--project=${PROJECT_ID} \
--direction=INGRESS \
--priority=1000 \
--network=${NETWORK} \
--action=ALLOW \
--rules=${RULES} \
--source-ranges=${IP_ADDRESS}
gcloud compute firewall-rules create k3s --allow=tcp6:6443 --target-tags=k3s
```

```shell
FIREWALL_RULES_NAME=nfs
PROJECT_ID=kktyn-dev-2
NETWORK=default
RULES="tcp:111,udp:111,tcp:2049,udp:2049"
IP_ADDRESS="0.0.0.0/0"
TAGS="nfs-server"
gcloud compute firewall-rules create ${FIREWALL_RULES_NAME} \
--description="Allow test from nfs server" \
--project=${PROJECT_ID} \
--direction=INGRESS \
--priority=1000 \
--network=${NETWORK} \
--action=ALLOW \
--rules=${RULES} \
--source-ranges=${IP_ADDRESS} \
--target-tags=${TAGS}
```

1. Delete
```shell
FIREWALL_RULES_NAME=nfs
gcloud compute firewall-rules delete ${FIREWALL_RULES_NAME} \
--quiet
# Multi
FIREWALL_RULES_NAME1=allow-test1-from-my-home-ip
FIREWALL_RULES_NAME2=allow-test2-from-my-home-ip
gcloud compute firewall-rules delete ${FIREWALL_RULES_NAME} \
--quiet
```

# Related articles

# Back to the [README.MD][readme]

[readme]:<./README.MD>
