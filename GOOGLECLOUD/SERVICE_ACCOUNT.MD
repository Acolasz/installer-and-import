# Service Account

## Added/Delete role

```shell
# Added
PROJECT_ID=kktyn-dev-2
SERVICE_ACCOUNT=1037197631166-compute@developer.gserviceaccount.com
ROLE=roles/compute.instanceAdmin
gcloud projects add-iam-policy-binding ${PROJECT_ID} \
--member=serviceAccount:${SERVICE_ACCOUNT} \
--role=${ROLE}
```