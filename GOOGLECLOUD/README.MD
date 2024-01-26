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

# Api list

* [Compute Engine API][compute_engine]
* [VPC Network][vpc_network]
* [OS Login][os_login]
* [IAM][iam]
* [Kubernetes Engine][kubernetes_engine]
* [Project][project]
* [ServiceAccount][service_account]
* [SQL Instance][sql_instance]
* [Filestore][filestore]

[compute_engine]:<./COMPUTE_ENGINE.MD>

[vpc_network]:<VPC_NETWORK.MD>

[os_login]:<./OS_LOGIN.MD>

[iam]:<./IAM.MD>

[kubernetes_engine]:<./KUBERNETES_ENGINE.MD>

[project]:<./PROJECT.MD>

[service_account]:<SERVICE_ACCOUNT.MD>

[sql_instance]:<SQL_INSTANCE.MD>

[filestore]:<FILESTORE.MD>
