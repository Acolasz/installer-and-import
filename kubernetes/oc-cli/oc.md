# OC usage

## Install for Linux

```shell
sudo ln -s /opt/oc-4.7.1 /opt/oc
```

## Login

```shell
oc login https://api.okd.dorsum.intra:6443
oc login
// oc 4.7.6 version
https://oauth-openshift.apps.okd.dorsum.intra/oauth/token/request
oc login --token=<ADuser_token> --server=https://api.okd.dorsum.intra:6443
```

## Project

```shell
oc new-project clavisnxt-bundle-otp --display-name="clavisnxt-bundle-otp"
//List projects
oc projects
oc get projects  
//Change project
oc project clavisnxt-bundle-otp
//Check current project
oc project
oc project -q
//Delete project
oc delete project clavisnxt-bundle-otp
```

/************************/
/**** ServiceAccount ****/
// create SA
oc create serviceaccount jenkins-tst -n <namespace>
// added permission to SA
oc adm policy add-cluster-role-to-user edit system:serviceaccount:default:jenkins-tst
// get SA
oc get sa
oc get serviceaccount
oc get sa --all-namespaces
oc get serviceaccount --all-namespaces
// desccribe SA
oc describe sa robot
oc describe serviceaccount robot
// delete SA
oc delete sa robot
oc delete serviceaccount robot
/****************************/
/**** Project permission ****/
// for group
oc adm policy add-role-to-group -h
oc adm policy add-role-to-group <role> <group_name> --rolebinding-name='<rolebinding_name>' -n <project>
/********************/
/**** Deployment ****/
// Get
oc get deployment
oc get deployment -o json | jq '.items[]'
oc get deployment -o json | jq '.items[] | select(.status.reason!=null)'
oc get deployment -o json | jq '.items[] | (.metadata.name)'

// Describe
oc describe deployment <deployment_name>
/** Scaling **/
oc scale deployment <deployment_name> --replicas=1
/**********************/
/**** Image-Stream ****/
oc import-image <image_stream-name>:version --from=<image_from_registry> --confirm --insecure=true
oc import-image clavisnxt-node-otp:20.10.1-latest --from=artifactory-nxt.dorsum.eu/docker/otp/clavis-nxt-bundle-node-docker-otp/20.10.1/clavis-nxt-bundle-node-docker-otp:20.10.1-latest --confirm --insecure=true

/*****************************************/
/**** CREATE APP (DEPLOYMENT, SERVICE) ***/
oc new-app <image_stream-name>:version
oc new-app clavisnxt-node-otp:20.10.1-latest
// DEPLOYMENT YAML
spec:
template:
spec:
containers:
- name:
imagePullPolicy: Always
serviceAccountName: runasanyuid
serviceAccount: runasanyuid

/*******************/
/**** CONFIGMAP ****/
oc create configmap <configmap_name> \
--from-file=./path/to/file/prop.properties
oc create configmap datasources-properties \
--from-file=./install/installation-guide/localhost/properties_files/datasources.properties
spec:
template:
spec:
containers:
- name:
envFrom:
- configMapRef:
name: service-properties
/**********************/
/**** UDP protokol ****/
/* @see https://docs.openshift.com/container-platform/4.4/networking/openshift_sdn/enabling-multicast.html */
oc annotate netnamespace project-bundle netnamespace.network.openshift.io/multicast-enabled=true
/*****************************/
/**** Pods *******************/
oc get pods
oc get pods -o json | jq '.items[] | (.metadata.ownerReferences)'
/** ReplicaSet **/

/*****************************/
/**** Remove evicted pods ****/
/* @see https://sachsenhofer.io/how-to-remove-evicted-pods-in-kubernetes-openshift/ */
oc get pods --all-namespaces -o json | jq '.items[] | select(.status.reason!=null) | select(.status.reason | contains("Evicted")) | "oc delete pods \(.metadata.name) -n \(.metadata.namespace)"' | xargs -n 1 bash -c
oc get pods --all-namespaces -o json | jq '.items[] | select(.status.reason!=null) | select(.status.reason | contains("Running")) | xargs -n 1 bash -c'
/*****************************/
/**** Delete Node steps ******/
/* @see https://www.techbeatly.com/openshift-cluster-how-to-drain-or-evacuate-a-node-for-maintenance/ */
sudo podman images
oc get nodes | grep compute-102
// Mark as Unschedulable
oc adm cordon worker1
oc adm cordon worker2
oc adm cordon worker3
// Mark as Schedulable
oc adm uncordon worker1
oc adm uncordon worker2
oc adm uncordon worker3
// drain node
oc adm drain worker3
oc adm drain worker3 --delete-local-data=false --ignore-daemonsets=false  --grace-period=120 --force=true
oc adm drain worker3 --delete-local-data=false --ignore-daemonsets=false  --grace-period=120 --pod-selector='' --force=true
oc adm drain worker3 --delete-local-data=false --ignore-daemonsets=false  --grace-period=120 --selector='' --force=true
oc adm drain worker3 --force --delete-local-data --ignore-daemonsets
// get/describe node
oc get node worker3
oc describe node worker3
// View resource nodes
oc adm top nodes
/*************************/
/** Evicted pods delete **/
// https://sachsenhofer.io/how-to-remove-evicted-pods-in-kubernetes-openshift/
// all namespace
oc get pods --all-namespaces -o json | jq '.items[] | select(.status.reason!=null) | select(.status.reason | contains("Evicted")) | "oc delete pods \(.metadata.name) -n \(.metadata.namespace)"' | xargs -n 1 bash -c
// custome namespace
oc get pods -n <namespace> -o json | jq '.items[] | select(.status.reason!=null) | select(.status.reason | contains("Evicted")) | "oc delete pods \(.metadata.name) -n \(.metadata.namespace)"' | xargs -n 1 bash -c
/*************************/
/** Node debug ***********/
oc debug node/worker3
oc debug node/worker3
#chroot /host
#sudo su