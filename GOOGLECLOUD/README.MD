# Google Kubernetes Cluster

## Create cluster

```shell
gcloud container --project "gke-oo-test-1" \
clusters create "oo-cluster-3" \
--machine-type n1-standard-2 \
--num-nodes 2 \
--region "europe-west1" \
--release-channel "regular" \
--network "projects/gke-oo-test-1/global/networks/default" \
--subnetwork "projects/gke-oo-test-1/regions/europe-west10/subnetworks/default" \
--cluster-version latest \
--cluster-ipv4-cidr "/17"
```

> Tip: [Regions and zones][vm_and_regio]

### Delete cluster

```shell
gcloud container --project "gke-oo-test-1" \
clusters delete "autopilot-cluster-1" \
--location "us-central1" \
--quiet
```

## Create "worker node"

```shell
gcloud container node-pools create oo-node-1 \
--cluster=oo-cluster-2 \
--machine-type=MACHINE_TYPE \
--num-nodes=NUM_NODES \
--zone=ZONE
# OR
gcloud container --project "gke-oo-test-1" \
node-pools create "oo-node-1" \
--machine-type n1-standard-2 \
--num-nodes 0 \
--enable-autoscaling \
--min-nodes 0 \
--max-nodes 3 \
--node-labels oo.gke/node-purpose=user \
--region "europe-west10" \
--cluster "oo-cluster-2"
```

# Connect to cluster

## Create credential in the cluster

```shell
gcloud container --project "gke-oo-test-1" \
clusters get-credentials "oo-cluster-2" \
--region "europe-west10"
```

### Testing the connection

```shell
kubectl get node
kubectl get namespace
```

# Related articles

* DevOps school: [Create Google Kubernetes Engine(GKE) Cluster using command line][devops_school]
* [Kubernetes on Google Cloud (GKE)][step_zero_gcp]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[devops_school]:<https://www.devopsschool.com/blog/create-google-kubernetes-enginegke-cluster-using-command-line/>

[step_zero_gcp]:<https://z2jh.jupyter.org/en/latest/kubernetes/google/step-zero-gcp.html>

[vm_and_regio]:<https://cloud.google.com/compute/docs/regions-zones/#available>