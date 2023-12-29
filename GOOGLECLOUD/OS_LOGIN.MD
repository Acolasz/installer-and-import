# Cloud OS Login API

## Profile

```shell
# Describe
PROJECT_ID=kktyn-dev-1
gcloud compute os-login describe-profile \
--project=${PROJECT_ID} \
--format='yaml(posixAccounts[].accountId)'
# Remove
gcloud compute os-login remove-profile

PROJECT_ID=kktyn-tst-1
gcloud compute os-login remove-profile \
--project=${PROJECT_ID} \
--quiet
```

## SSH key

```shell
# List
gcloud compute os-login ssh-keys list
FINGERPRINT                                                       EXPIRY
faa658fb8ac083636aed5b2c5feca00fa93e9bd2b283c64e217c839715c72caa
220be6c8d62b1283c9c3ecffde7b5f783ed3258892191897264910671302a0c3

# Describe
FINGERPRINT=220be6c8d62b1283c9c3ecffde7b5f783ed3258892191897264910671302a0c3
gcloud compute os-login ssh-keys describe --key=${FINGERPRINT}

# Add
PROJECT_ID=kktyn-dev-1
SSH_KEY_PUB=/Users/user/.ssh/olaszkukutyin.pub
gcloud compute os-login ssh-keys add \
--key-file=${SSH_KEY_PUB} \
--project=${PROJECT_ID}

# Remove
FINGERPRINT=faa658fb8ac083636aed5b2c5feca00fa93e9bd2b283c64e217c839715c72caa
gcloud compute os-login ssh-keys remove --key=${FINGERPRINT}
```

# Related articles

* Google cloud - [Cloud OS Login API][create_instance_template]
* Google cloud - [gcloud compute os-login][cmd_os_login]

# Back to the [README.MD][readme]

[readme]:<./README.MD>

[os_login_cmd]:<https://cloud.google.com/sdk/gcloud/reference/compute/os-login>

[os_login_api]:<https://cloud.google.com/compute/docs/oslogin/rest/>


