# VPC Network

## Cloud Firewall policies

### Firewall policies

#### VPC firewall rules

##### Create firewall rule

```shell
FIREWALL_RULES_NAME=allow-ssh-from-my-home-ip
PROJECT_ID=kktyn-dev-1
NETWORK=default
RULES=tcp:22
IP_ADDRESS=44.55.192.88/1
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

# Related articles

# Back to the [README.MD][readme]

[readme]:<./README.MD>
