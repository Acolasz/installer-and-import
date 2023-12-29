# Usage

## General setup

* Quiet mode (yes)
    * `gcloud compute instance-templates delete ${INSTANCE_TEMPLATE_NAME} --quiet`
    * `gcloud projects delete ${PROJECT_ID} --quiet`
* Set project/region
    ```shell
    # Set project
    PROJECT_ID=kktyn-dev-1
    gcloud config set project ${PROJECT_ID}
    # Set region
    REGION=europe-west3
    gcloud config set region ${REGION}
    ```
