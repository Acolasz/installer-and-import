/*************************************************************************************************/
/*************************************** Commands ************************************************/
/*************************************************************************************************/
/***********************************************************************/
/********************************* BUILD TOOL **************************/
/***********************************************************************/
/***********************************************************************/
/********************* GRADLE ************************/
/*****************************************************/
// gradle setup
GRADLE_HOME=/opt/gradle-6.8.3
PATH=$GRADLE_HOME/bin:$PATH
gradle -v
// init
gradle init
/*****************************/
/********* WRAPPER ***********/
/** command **/
./gradlew <tasks>
// wrapper verison
./gradlew --version
// wrapper verison upgrade
./gradlew wrapper --gradle-version 7.4.2 --distribution-type=bin
// List tasks
./gradlew tasks
/*****************************************************/
/********************* NVM ***************************/
/*****************************************************/
/** Download **/
https://github.com/coreybutler/nvm-windows/releases 
/** install for windows **/
https://stackoverflow.com/questions/25654234/node-version-manager-nvm-on-windows
installed: 
	c:\Portable\nvm\nvm-1.1.7\nvm\
//
nvm version
nvm install <nodejs_version>
nvm install 10.15.1
// installed: 
//	c:\Portable\nvm\nvm-1.1.7\nvm\v10.15.1
// symlink to c:\Portable\nodejs
nvm list
nvm use <nodejs_version>
nvm use 10.15.1
/** Transitive Dependencies **/
https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Transitive_Dependencies
/*****************************************************/
/********************* NPM ***************************/
/*****************************************************/
/*************************************/
/**** evironment variables ***********/
// https://docs.npmjs.com/cli/v9/using-npm/config#environment-variables
// https://gist.github.com/moos/4635bda5b04dc8113d8ea7ee974cabc2
npm config ls -l
// variable config
NPM_CONFIG_FOO=bar
/******************************/
/**** npm lifecycle ***********/
npm ci --only=production
/*********************/
/**** .npmrc *********/
Checking for configuration file "/var/jenkins/workspace/own-app/.npmrc".
Checking for configuration file "/home/jenkins/.npmrc".
Found configuration file "/home/jenkins/.npmrc".
Checking for configuration file "/opt/nodejs-8.11.3/etc/npmrc".
Checking for configuration file "/var/jenkins/workspace/own-app/.npmrc".
Checking for configuration file "/var/jenkins/workspace/.npmrc".
Checking for configuration file "/var/jenkins/.npmrc".
Checking for configuration file "/var/.npmrc".
/**********************/
/**** Cache ***********/
NPM_CONFIG_PREFIX=./npm
NPM_CONFIG_CACHE=${NPM_CONFIG_PREFIX}/cache
npm cache clean --force
/*****************************************************/
/********************* Yarn **************************/
/*****************************************************/
/** install **/
yarn install v1.22.10
/** Artifactory auth **/
#User specific npm registry settings
curl -u ${artifactory_user}:${artifactory_password} ${artifactory_url}/api/npm/auth >>.npmrc
/*****/
https://www.whitesourcesoftware.com/free-developer-tools/blog/npm-vs-yarn-which-should-you-choose/
/** Cache **/
https://classic.yarnpkg.com/en/docs/cli/cache/
yarn cache list --pattern lodash
yarn cache dir
// Cache versiója v1, v2, v4, v6
A yarn cacheelése is függ a yarn verziójától, ezzel együtt a cache mappa is!
/** Locations .yarnrc **/
14:06:55  verbose 0.394 Checking for configuration file "/var/jenkins-nxt-prd/workspace/MYW_mw-web_diofa_1.70.1-nxt-test/.yarnrc".
14:06:55  verbose 0.395 Checking for configuration file "/home/DORSUM/jenkins-prd/.yarnrc".
14:06:55  verbose 0.395 Found configuration file "/home/DORSUM/jenkins-prd/.yarnrc".
14:06:55  verbose 0.395 Checking for configuration file "/opt/nodejs-8.11.3/etc/yarnrc".
14:06:55  verbose 0.395 Checking for configuration file "/var/jenkins-nxt-prd/workspace/MYW_mw-web_diofa_1.70.1-nxt-test/.yarnrc".
14:06:55  verbose 0.396 Checking for configuration file "/var/jenkins-nxt-prd/workspace/.yarnrc".
14:06:55  verbose 0.396 Checking for configuration file "/var/jenkins-nxt-prd/.yarnrc".
14:06:55  verbose 0.396 Checking for configuration file "/var/.yarnrc".
/** Global install to NodeJS **/
npm install -g yarn
npm install -g yarn@1.22.5
/** Add package **/
https://classic.yarnpkg.com/en/docs/cli/add/
yarn add package-name@1.2.3
/** Trouble shooting **/
// no space left on device
// "ENOSPC: no space left on device, open '/opt/jenkins/workspace/custom-repo/yarn-error.log'"
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
/*****************************************************/
/********************* Bower *************************/
/*****************************************************/
https://www.npmjs.com/package/bower-dependency-tree
bower-dependency-tree
bower-dependency-tree --log-level
/*****************************************************/
/********************* SonarScanner ******************/
/*****************************************************/
/*****************************/
/********* sonar-scanner-cli */
SONAR_SCANNER_VERSION=4.8.0
SONAR_SCANNER_BUILD_NUMBER=2856
curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-${SONAR_SCANNER_VERSION}.${SONAR_SCANNER_BUILD_NUMBER}-linux.zip \
-o sonar-scanner-cli-${SONAR_SCANNER_VERSION}-linux.zip
unzip ./sonar-scanner-cli-${SONAR_SCANNER_VERSION}-linux.zip -d /opt/
mv ./sonar-scanner-${SONAR_SCANNER_VERSION}.${SONAR_SCANNER_BUILD_NUMBER}-linux /opt/sonar-scanner-${SONAR_SCANNER_VERSION} 
/***********************************************************************/
/********************************* GIT *********************************/
/***********************************************************************/
git config --global user.email "oo@dorsum.eu"
git config --global user.name "Olivér"
/*****************************************************/
/********************* gitignore *********************/
/*****************************************************/
https://github.com/github/gitignore
/*****************************************************/
/********************* gitattributes *****************/
/*****************************************************/
https://github.com/alexkaratarakis/gitattributes/blob/master/.gitattributes
// Attribute sets
git ls-files | git check-attr -a --stdin
/*****************************************************/
/********************* Environment Variable **********/
/*****************************************************/
https://git-scm.com/book/en/v2/Git-Internals-Environment-Variables
/**********************/
/**** Committing ******/
GIT_AUTHOR_NAME is the human-readable name in the “author” field.
GIT_AUTHOR_EMAIL is the email for the “author” field.
GIT_AUTHOR_DATE is the timestamp used for the “author” field.
GIT_COMMITTER_NAME sets the human name for the “committer” field.
/**********************/
/**** Verbose *********/
# Linux
export GIT_TRACE_PACKET=1
export GIT_TRACE=1
export GIT_CURL_VERBOSE=1
/*****************************************************/
/********************* Push **************************/
/*****************************************************/
// error: RPC failed; HTTP 500 curl 22 The requested URL returned error: 500
// send-pack: unexpected disconnect while reading sideband packet
// Writing objects: 100% (50/50), 77.67 MiB | 25.22 MiB/s, done.
// Total 50 (delta 7), reused 0 (delta 0), pack-reused 0
// fatal: the remote end hung up unexpectedly
git config --global http.postBuffer 500M
git config --global http.maxRequestBuffer 100M
/*****************************************************/
/********************* Copy **************************/
/*****************************************************/
git checkout <otherbranch> <path/to/file>
/*****************************************************/
/********************* SUBMODULE *********************/
/*****************************************************/
// Change branch
git submodule add --name installation-guide -b release/20.10.01 https://stash.dorsum.eu/scm/otp/clavis-nxt-installation-guide-otp.git ./install/clavis-nxt-installation-guide-otp/
git submodule init
// Submodule remote refresh
git submodule update --init
// if there are nested submodules:
git submodule update --init --recursive
// pull all changes for the submodules
git submodule update --remote
/* submodule remove */
delete .gitmodules file
delete  submodul section from .git/config file
git add .gitmodules
git rm --cached .git/modules/installation-guide/
rm -rf .git/modules/installation-guide/
git submodule deinit ./install/clavis-nxt-installation-guide-otp/
git rm ./install/clavis-nxt-installation-guide-otp/
// remote commit !!
git commit -m "Removed submodule "
rm -rf .git/modules/installation-guide/
/*****************************************************/
/********************* RESET *************************/
/*****************************************************/
// Discard
git reset --hard HEAD~1
// before last commit
git reset --soft HEAD~1
git reset --mixed HEAD~1
git reset --hard HEAD~1
/*****************************************************/
/********************* COMMIT ************************/
/*****************************************************/
/*****************************/
/**** Merge revert commit ****/
// álljunk, amibe mergeltük ágra,
// -m 1|2 flaghez trartozó számok a revertálni kívánt commit őseit jelenti
// válasszuk az 1-est és felugró ablak után ENTER és lépjünk ki
// Létrejött Revert commit-ot ellenőrizzük, hogy a megfelelő változtatások vannak e benne
// majd push
git revert <commit_hash> -m 1

/**********************/
/**** Empty commit ****/
git commit --allow-empty -m "test empty commit" && git push -u origin <branch>
/***************************/
/**** Windows credetial ****/
// remove: 
// - passwd file ins sourcetre
// - windows credentials bitbucket.dorsum.eu
git config --global credential.helperselector.selected wincred
/*********************/
/**** Remote list ****/
git remote -v
/***************************/
/**** Update .git cache ****/
git rm -r --cached .
git clean -fxd
git restore .
git add .
/*******************/
/** empty objects **/
https://stackoverflow.com/questions/11706215/how-can-i-fix-the-git-error-object-file-is-empty
git fsck --full
rm .git/objects/8b/61d0135d3195966b443f6c73fb68466264c68e
git update-ref HEAD 9f0abf890b113a287e10d56b66dbab66adc1662d
/***********************************************************************/
/********************************* OPENSHIFT ***************************/
/***********************************************************************/
/*****************************************************/
/********************* Üzemeltetés *******************/
/*****************************************************/
/**********************/
/**** ETCD backup *****/
https://docs.openshift.com/container-platform/4.5/backup_and_restore/backing-up-etcd.html#backing-up-etcd-data_backup-etcd
/****************************/
/**** Gracefull shutdown ****/
https://docs.openshift.com/container-platform/4.5/backup_and_restore/graceful-cluster-shutdown.html#graceful-shutdown-cluster

/****************************/
/**** Allocated resource ****/
/* system-reserved */
// latest
https://docs.okd.io/latest/nodes/nodes/nodes-nodes-resources-configuring.html
// 4.5
https://docs.openshift.com/container-platform/4.5/nodes/nodes/nodes-nodes-resources-configuring.html
/***********************************************/
/**** Understanding Local Ephemeral Storage ****/
https://docs.openshift.com/container-platform/4.5/storage/understanding-ephemeral-storage.html
https://kubernetes.io/docs/concepts/configuration/manage-resources-containers/#local-ephemeral-storage
// az erőforrások leginkább felhasznált CPU, RAM-on kívül 
// a file rendszer mérete is befolyásolja a működését a környzetnek.
// az OpenShift (kubernetes) fenn tart magának helyet a működésnek, kublet-config, és fenn tartja a pod-oknak is. Ha a kublet-config-nak nem jutna elég hely a működéshez,
// akkor a rendszer a pod-okat .
https://docs.openshift.com/container-platform/4.5/scalability_and_performance/recommended-host-practices.html
/**** Partició ellenőrzés ****/
sudo su -

df -hT /
Filesystem     Type  Size  Used Avail Use% Mounted on
/dev/sda4      xfs   120G   79G   42G  66% /

lsblk
NAME   MAJ:MIN RM   SIZE RO TYPE MOUNTPOINT
sda      8:0    0   240G  0 disk
|-sda1   8:1    0   384M  0 part /boot
|-sda2   8:2    0   127M  0 part /boot/efi
|-sda3   8:3    0     1M  0 part
`-sda4   8:4    0 239.5G  0 part /sysroot
sr0     11:0    1   711M  0 rom

/**** Parancsok install ****/
sudo yum -y install cloud-utils-growpart gdisk

/**** Partició növelés ****/
https://computingforgeeks.com/resize-ext-and-xfs-root-partition-without-lvm/
sudo growpart /dev/sda 4
CHANGED: partition=4 start=1050624 old: size=250607583 end=251658207 new: size=502265823 end=503316447

// type: xfs
sudo xfs_growfs /sysroot/
meta-data=/dev/sda4              isize=512    agcount=219, agsize=143423 blks
         =                       sectsz=512   attr=2, projid32bit=1
         =                       crc=1        finobt=1, sparse=1, rmapbt=0
         =                       reflink=1
data     =                       bsize=4096   blocks=31325947, imaxpct=25
         =                       sunit=0      swidth=0 blks
naming   =version 2              bsize=4096   ascii-ci=0, ftype=1
log      =internal log           bsize=4096   blocks=2560, version=2
         =                       sectsz=512   sunit=0 blks, lazy-count=1
realtime =none                   extsz=4096   blocks=0, rtextents=0
data blocks changed from 31325947 to 62783227
// check after sudo xfs_growfs /sysroot/
df -hT /
Filesystem     Type  Size  Used Avail Use% Mounted on
/dev/sda4      xfs   240G   80G  161G  34% /

/*****************************************************/
/********************* HELM **************************/
/*****************************************************/
helm repo add helm <url> --username <user> --password <userpassword>
helm pull helm/<chartname>
helm lint -f ./default-simple-service-override.yaml.yaml ./default-simple-service
helm template -f ./default-simple-service-override.yaml.yaml ./default-simple-service
helm package ./default-simple-service
helm install -f myvalues.yaml -f override.yaml  myredis ./redis
helm install wmp-srv-sarsn -f ./default-simple-service-override.yaml.yaml ./default-simple-service-1.0.0.tgz
helm list
helm repo update helm
helm search repo helm
helm show all
helm registry login https://artifactory.dorsum.eu/artifactory/helm-manual-local/ --username oo --password AKCp8k7uYNRRWDsRniSMzBuyTkKsdcEwCkunLaoVb1gu5Vwj67oogcKnMBf24MHAxwTEL6x2f
// artifactory plugin install and usage
helm repo add helm-manual-local https://artifactory.dorsum.eu/artifactory/helm-manual-local/ --username oo --password AKCp8k7uYNRRWDsRniSMzBuyTkKsdcEwCkunLaoVb1gu5Vwj67oogcKnMBf24MHAxwTEL6x2f
helm repo add helm-manual-local https://artifactory.dorsum.eu/artifactory/helm-manual-local/ --username oo --password AKCp8k7uYNRRWDsRniSMzBuyTkKsdcEwCkunLaoVb1gu5Vwj67oogcKnMBf24MHAxwTEL6x2f
helm plugin install https://github.com/belitre/helm-push-artifactory-plugin --version 1.0.1
helm plugin list
helm push-artifactory java-springboot-1.0.0.tgz helm-manual-local
/*****************************************************/
/********************* OC ****************************/
/*****************************************************/
/***************************/
/**** Install for Linux ****/
sudo ln -s /opt/oc-4.7.1 /opt/oc
/***************/
/**** login ****/
oc login https://api.okd.dorsum.intra:6443
oc login
// oc 4.7.6 version
https://oauth-openshift.apps.okd.dorsum.intra/oauth/token/request
oc login --token=<ADuser_token> --server=https://api.okd.dorsum.intra:6443

/*****************/
/**** Project ****/
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
/*************************/
// https://access.redhat.com/webassets/avalon/j/includes/session/scribe/?redirectTo=https%3A%2F%2Faccess.redhat.com%2Fsolutions%2F5610941
// https://access.redhat.com/solutions/6738851
// Manually clearing unnecessary container resources
sudo crictl ps -a | grep -i exited
for id in $(sudo crictl ps -a | grep -i exited | awk '{print $1}') ; do sudo crictl rm $id ; done
crictl rmp `crictl pods -q -s NotReady`
crictl rm `crictl ps -q --state Exited`
sudo crictl rmi --prune
/*************************/
// Helpful Data
/etc/kubernetes
/var/lib/kubelet
/*************************/
oc adm node-logs worker3 -u kubelet
https://faun.pub/node-kubelet-troubleshooting-for-openshift-10e7009af9c4
/*****************************/
/**** LDAP Group sync ********/
oc adm groups sync --sync-config=/etc/config/ldap-group-sync.yaml --confirm
oc adm groups sync --sync-config=/etc/config/ldap-group-sync.yaml --confirm --loglevel=10
ldap-group-sync.yaml
kind: LDAPSyncConfig
    apiVersion: v1
    url: ldap://192.168.1.1:443
    insecure: true
    bindDN: <tech-user>
    bindPassword: <tech-user-passwd>
    rfc2307:
        groupsQuery:
            baseDN: "OU=<group>,OU=DomainGroups,DC=domain,DC=valami"
            scope: sub
            filter: "(objectClass=group)"
            derefAliases: never
            pageSize: 0
        groupUIDAttribute: dn
        groupNameAttributes: [ cn ]
        groupMembershipAttributes: [ member ]
        usersQuery:
            baseDN: "OU=<group>,OU=DomainUsers,DC=domain,DC=valami"
            scope: sub
            derefAliases: never      
            pageSize: 0
        userUIDAttribute: dn
        userNameAttributes: [ sAMAccountName ]
        tolerateMemberNotFoundErrors: true
        tolerateMemberOutOfScopeErrors: true
/***********************************************************************/
/********************************* ANSIBLE *****************************/
/***********************************************************************/
/*****************************************************/
/********************* Playbook **********************/
/*****************************************************/
/*****************************/
/********* ROLE **************/
/*****************************/
/*****************************/
/**** templates **************/
/*****************************/
/** online j2 rendered **/
https://j2live.ttl255.com/

/***********************************************************************/
/********************************* UNIX ********************************/
/***********************************************************************/
/** pacakge update **/
apt --only-upgrade install git
apt --only-upgrade install git-man
// Check
apt list git
apt list --installed git
/* server reboot */
reboot
// server time of up
uptime
/* SSH */
ssh-keygen -b 2048 -t rsa -C johndoe@kukutyin.hu
/* copy command */
// copy to two machine between
scp -r /opt/keycloak sysadmin@192.168.15.21:/opt/
// copy with folders
cp -R /opt/jenkins-prd/* /opt/jenkins-tst/
// better copy with folders and hidden files
cp -rT /opt/jenkins-prd /opt/jenkins-tst
/* copy command */
// change file/folder owner and group
chown -R <user> /path/to/
chown -R <user>:<group> /path/to/
chgrp -R <group> /path/to/
// change to "root" user
sudo su -
// List debian repositories
/** Related article:
 * https://difyel.com/linux/usr/bin/apt-key/
 */
man sources.list 
/etc/apt/sources.list
/etc/apt/sources.list.d/
// list installed package
sudo apt list --installed
// trusted package
/etc/apt/trusted.gpg.d
/* apt-key */
apt-key list

sudo dpkg-query -l
/** zip - unzip with tar.gz **/
// Create tar.gz Archive File
tar cvzf myImages-14-09-12.tar.gz /home/MyImages
// OR
tar cvzf myImages-14-09-12.tgz /home/MyImages
tar -czvf pgsql.tar.gz /home/MyImages/file.txt
// OR
tar -xvf myImages-14-09-12.tgz file1 file2 file3 dir1 --strip 1 -C /path/to/dir/
tar zxvf myImages-14-09-12.tgz file1 file2 file3 dir1 --strip 1 -C /path/to/dir/
// Uncompress tar.gz Archive File
tar zxvf ./jdk-7u80-linux-x64.tar.gz --strip 1 -C /opt/oracle-7u80-jdk/ 
tar -xzvf ./pgsql.tar.gz -C /opt/oo
tar -xvf myImages-14-09-12.tar.gz -C /path/to/dir/
// List tar.gz content
tar -ztvf projects.tar.gz
/** zip - unzip with .zip file **/
sudo apt-get install -y unzip zip
// Uncompress .zip Archive File
unzip file.zip -d destination_folder
/** service parancsok **/
/** 
 * https://www.digitalocean.com/community/tutorials/how-to-use-systemctl-to-manage-systemd-services-and-units 
 */
// service-ek listája
systemctl list-units --type=service
/** service **/
journalctl -xe
journalctl -u jenkins.service
journalctl --unit jenkins --since  "2016-10-30 18:17:16"
// service fájl
/etc/systemd/system/docker.service
// reload service update
systemctl daemon-reload
// service parancsok
systemctl status docker.service
systemctl stop docker.service
systemctl start docker.service
// check status
systemctl is-active application.service
systemctl is-enabled application.service
// show service content
systemctl cat atd.service
/** user crontab **/
https://www.geeksforgeeks.org/crontab-in-linux-with-examples/
crontab -l
//allow another user, add running user
sudo nano /etc/cron.allow
// or
sudo nano /etc/cron.d/cron.allow
//added cron job another user
crontab -u <user> -l
crontab -u <user> -e
//sample
35 1 */1 * * /home/user/crontab-test.sh --cron >/dev/null 2>&1
// cron log
sudo less /var/log/cron
// or
sudo less /var/log/syslog
/** vi usage **/
vi <filename>
press i or a
ESC
:wq
// if no write in file
:q!
/** particiók mérete **/
fdisk -l
dmesg | grep -i sda
df -hT /
lsblk
/** könyvtárak méretét listázzuk **/
df -h
du -sh /var
du -shc /var
//du -shc /var/* 
du -h --max-depth=1 /var
du -b --max-depth=1 /var
/** futó processeket **/
ps -ef
ps auxww
/** használt portok és ipcímek **/
netstat -anp
netstat -aun
netstat -tulpn
/** CPU, memory usage **/
prstat -t
// adott userrel indított processeket mutatja
prstat -u <user>
// fájlokba keresi a greppelt szöveket és kilistázza a fájlt és a sort, ahol ez található
find ./ -name 'iq.log.2020-04-23*' -exec grep 'AppleNotificationHandler' {} \; -print
find ./install/installation-guide -name assetbasket -exec ls -l {} ;\ 
/** User and Group **/
// Create group
sudo groupadd docker
// Add a user from group
sudo usermod -aG docker $USER
// Remove a user from group
sudo gpasswd -d $USER docker
// To activate the changes to group/s
newgrp docker
// list exists group
compgen -g
getent group		//more informations
// List all members of a group
grep -i --color 'ftponly' /etc/group
// List all group of a user
groups $USER
/** sysctl **/
sysctl -w vm.max_map_count=262144
// OR
/etc/sysctl.conf
/** facl **/
setfacl -m u:postgres:rwx /var/log/postgresql/
/** CURL **/
curl --insecure -i -d "clientIdentifierType=CIT_1004&brand=5000&IMPgIndx=1&IMPgRcCnt=500&clientId=22052&clientId=22345"  http://erstelnx:7001/ihs-adapter/restapi/clientlist/search
curl -X GET "http://dit19mpc014.dorsum.intra:9090/actuator" -H "Authorization: Basic X2NsYXZpc19wYl93czpfY2xhdmlzX3BiX3dz"
curl -X POST http://erstelnx:7001/ihs-adapter/restapi/investmentaccountdetails/search?investmentAccountNumberType=IAIT_1&clientIdentifierType=CIT_1004&brand=5000&IMPgRcCnt=5&IMPgIndx=1
curl -o file.html http://erstelnx:7001/ihs-adapter/file.html
/** WGET **/
wget --no-cookies --no-check-certificate --header="X-JFrog-Art-Api: ${ARTIFACTORY_TOKEN}" https://artifactory.dorsum.eu/artifactory/docker-installer/oracle/jdk/jdk-${JAVA_VERSION}-linux-x64.tar.gz -O jdk-${JAVA_VERSION}-linux-x64.tar.gz
wget --header="X-JFrog-Art-Api: AKCp5fUDpkASSHdmtosbg8id1s7fnrLtqVq22J3BT1fBu58P3gF3ayfqYe53PqESr7WsZgvFg" http://artifactory-nxt.dorsum.eu/artifactory/devops-installer/oracle/jdk/jdk-8u261-linux-x64.tar.gz
wget --header="Authorization: Basic X2NsYXZpc19wYl93czpfY2xhdmlzX3BiX3dz" http://artifactory-nxt.dorsum.eu/artifactory/devops-installer/oracle/jdk/jdk-8u261-linux-x64.tar.gz
wget -O- --header="Authorization: Basic <base64_encode>" --header='Accept: application/json' http://10.120.10.195:9000/api/cluster?pretty=true
wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" https://download.oracle.com/otn/java/jdk/7u80-b15/jdk-7u80-linux-x64.tar.gz?AuthParam=1626267242_9b298a76a4b214b888f121c3d85d98a3 -O jdk-7u80-linux-x64.tar.gz 
wget https://archive.apache.org/dist/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz -O apache-maven-3.3.3-bin.tar.gz 
/** jq command **/
https://www.baeldung.com/linux/jq-command-json
/*****************************************************/
/********************* OSX ***************************/
/*****************************************************/
/******************/
/**** terminal ****/
/******************/
/** Shortcut **/
https://support.apple.com/en-us/HT201236
/** zsh install **/
https://sourabhbajaj.com/mac-setup/iTerm/zsh.html
https://github.com/ohmyzsh/ohmyzsh/wiki
/** zsh fancy feature **/
https://medium.com/@shivam1/make-your-terminal-beautiful-and-fast-with-zsh-shell-and-powerlevel10k-6484461c6efb
/** parallel command **/
COMMAND + SHIFT + I
https://medium.com/@wendymak/sending-commands-to-multiple-iterm-tabs-f18b66dc5387
/**************/
/**** DREW ****/
/**************/
/*****************************************************/
/*****************************************************/
/********************* BASH **************************/
/*****************************************************/
/*********************************/
/**** Paraméter helyettesítés ****/
https://www.cyberciti.biz/tips/bash-shell-parameter-substitution-2.html
/*****************************************************/
/********************* NETRC *************************/
/*****************************************************/
/** file neve **/
.netrc
// tartalma:
machine api.heroku.com
  login ocsko.oliver13@gmail.com
  password 39820363-ab1d-4cea-b076-2d1fe20dd608
machine git.heroku.com
  login ocsko.oliver13@gmail.com
  password 39820363-ab1d-4cea-b076-2d1fe20dd608 
/***********************************************************************/
/********************************* DOCKER ******************************/
/***********************************************************************/
/********************* Docker local registry *********/
/*****************************************************/
// with docker container
https://docs.docker.com/registry/deploying/
/********************* Docker daemon *****************/
/*****************************************************/
nohup bash -c "sudo dockerd 2>&1 &"
/********************* Commands **********************/
/*****************************************************/
/** Format **/
docker ps --format "{{.ID}} {{.Ports}} {{.Status}} {{.Names}}"
docker images --format="{{.ID}}"
/** system **/
docker system prune --force
docker rmi $(docker images --format="{{.ID}}")
/********************* Linux-on **********************/
/*****************************************************/
// probléma:
a docker image készítésekor nem tudunk leszedni linux-os package-ket vagy letölteni csomagolt fájlokat
// ip feloldás
saját linux-unkon az /etc/resolv.conf fájlt kell felülírni
/********************* Image Run *********************/
/*****************************************************/
/** PsAdmin **/
docker run --rm -p 7444:80 -e PGADMIN_DEFAULT_EMAIL=acolasz@postgres.local -e PGADMIN_DEFAULT_PASSWORD=acolasz --name pgadmin-1 -d dpage/pgadmin4
/********************* Image to/from tar *********************/
/*************************************************************/
// https://docs.docker.com/engine/reference/commandline/load/
docker load --input fedora.tar
docker load < busybox.tar.gz
// https://docs.docker.com/engine/reference/commandline/save/
docker save busybox > busybox.tar
docker save --output busybox.tar busybox
/********************* Image Tag *********************/
/*****************************************************/
sudo docker pull artifactory-nxt.dorsum.eu/docker/path:latest
sudo docker tag artifactory-nxt.dorsum.eu/docker/path:latest nexus-registry.dorsum.eu/docker-public/path:latest	
sudo docker push nexus-registry.dorsum.eu/docker-public/path:latest
/*********************  Remote docker deamon setup ***/
/*****************************************************/
echo "export DOCKER_HOST=tcp://10.120.10.210:2375" >> ~/.bashrc && source ~/.bashrc
/***********************************************************************/
/********************************* HEROKU ******************************/
/***********************************************************************/
/********************* Heroku CLI ********************/
/*****************************************************/
/** Download heroku CLI **/
curl https://cli-assets.heroku.com/install-ubuntu.sh | sh
heroku -v
heroku --help
//heroku/7.59.4 linux-x64 node-v12.21.0
/** Heroku login **/
heroku login
//heroku: Press any key to open up the browser to login or q to exit:
//press any key
//up browser and login
/** Create app with name **/
heroku create <app_name>
heroku stack:set container --app <app_name>
/** Create Heroku addons **/
//default postgresql version: 12
heroku addons:create heroku-postgresql:hobby-dev
heroku addons:create heroku-postgresql:hobby-dev --version=13 --name=postgresql-<app_name>-db
heroku pg:info
/***********************************************************************/
/********************************* AZURE DEVOPS ************************/
/***********************************************************************/
/********************* Rest API **********************/
/*****************************************************/
/** PAT **/
MY_PAT=yourPAT		# replace "yourPAT" with your actual PAT
B64_PAT=$(printf "%s"":$MY_PAT" | base64)
/** API list **/
curl https://$B64_PAT:dev.azure.com/{organization}/_apis/build-release/builds
/***********************************************************************/
/********************************* SQL *********************************/
/***********************************************************************/
/********************* Select ************************/
/*****************************************************/
/** Intervallum check **/
/***********************/
										S----E 3.
			S---------------E 2.	
					[START]----------------------------[END]
												S----------------E 1.
S:= "START_DATE"
E:= "END_DATE"
SELECT *
FROM "AO_08B316_CLIENT_DETAIL" WHERE
("START_DATE" <= [END] AND "END_DATE" >= [START]) OR --1.
("END_DATE" >= [START] AND "START_DATE" <= [END]) OR --2.
("START_DATE" >= [START] AND "END_DATE" <= [END]) --3.
/***********************************************************************/
/***********************************************************************/
/********************************* WINDOWS *****************************/
/***********************************************************************/
/*****************************************************/
/********************* NETRC *************************/
/*****************************************************/
/** file neve **/
_netrc
// tartalma:
machine api.heroku.com
  login ocsko.oliver13@gmail.com
  password 39820363-ab1d-4cea-b076-2d1fe20dd608
machine git.heroku.com
  login ocsko.oliver13@gmail.com
  password 39820363-ab1d-4cea-b076-2d1fe20dd608 
/*****************************************************/
/********************* Total Commander ***************/
/*****************************************************/
http://dirty-joe.com/
/*************/
/** Feature **/
/*************/
/** Colors **/
// https://ghisler.ch/board/viewtopic.php?t=22055
// Added new lines to APPDATA/Roaming/GHISLER/wincmd.ini
[Colors]
RedirectSection=%USERPROFILE%\Portable\tcm-plugins\ini\colors.ini
// Create file
%USERPROFILE%\Portable\tcm-plugins\ini\colors.ini
/*****************************************************/
/********************* POSTMAN *************************/
/*****************************************************/
/** ShortCut **/
https://elispostman.github.io/docs/v5/postman/launching_postman/navigating_postman
/*****************************************************/
/********************* PUTTY *************************/
/*****************************************************/
/** export **/
https://stackoverflow.com/questions/13023920/how-to-export-import-putty-sessions-list
// vagy 
Windows gomb és export, ahol kell és mentés
/** import **/
https://stackoverflow.com/questions/13023920/how-to-export-import-putty-sessions-list
// vagy
dupla klikk a mentett fájlon

/** Process list **/
wmic process where "name like '%java%'" get processid,commandline
/*****************************************************/
/********************* AD query **********************/
/*****************************************************/
/** User query **/
net user <AD name> /domain
/***********************************************************************/
/********************************* CI SYSTEMS **************************/
/***********************************************************************/
/***********************************************************************/
/********************************* JENKINS *****************************/
/***********************************************************************/
/** Build number increase **/
/***************************/
Jenkins.instance.getItemByFullName("projectName/repositoryName/release%2F1.0").updateNextBuildNumber(2)
Jenkins.instance.getItemByFullName("projectName/repositoryName/master").updateNextBuildNumber(5)

/***********************************************************************/
/********************************* SonarQube ***************************/
/***********************************************************************/
/** The admin user password change **/
user="admin"
new_salt=$(tr -dc A-Za-z0-9 </dev/urandom | head -c 40 ; echo '')
new_password="NEW-PASSWORD"
salted_password=$(echo -n "--${new_salt}--${new_password}--" | sha1sum | awk '{print $1}')
psql --username=sonar_tst sonarqube -c "UPDATE users SET crypted_password='$salted_password', salt='$new_salt', hash_method='SHA1' WHERE login='$user';"

/***********************************************************************/
/********************************* JFROG *******************************/
/***********************************************************************/
/*****************************************************/
/********************* ARTIFACTORY *******************/
/*****************************************************/
/** 	Migration		  **/
/***************************/
https://jfrog.com/knowledge-base/what-is-the-best-way-to-migrate-a-large-artifactory-instance-with-minimal-downtime/

/***********************************************************************/
/********************************* DATABASES ***************************/
/***********************************************************************/
/***********************************************************************/
/********************************* POSTGRESQL **************************/
/***********************************************************************/
// bash command: $
/**************************************/
/** Create OWNER (User, Role) AND DATABASE **/
$createuser <new_user_name>;
$psql
	ALTER USER <new_user_name> WITH ENCRYPTED PASSWORD <user_name_password>;
	CREATE DATABASE <new_database> OWNER <new_user_name>;
	GRANT ALL PRIVILEGES ON DATABASE <new_database> TO <new_user_name>;
/** Drop user/database and remove ROLE **/
$dropuser <user_name_1> -e;
$psql
	DROP OWNED BY <user_name_1>;
	DROP USER IF EXISTS <user_name_1>, <user_name_2>;
	DROP DATABASE <new_database>;
	exit;
/***********************/
/** List of databases **/
$psql
	\l
/*********************/
/** Change database **/
$psql
	\c <new_database>
// for example:
postgres=# \c <new_database>
You are now connected to database "<new_database>" as user "postgres".
<new_database>=#

/*******************/
/** List of roles **/
$psql
	\du
/*****************************************/
/** List of relations of <new_database> **/
// 1.
$psql
	\dt
// 2.
$psql
	SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';
/***********************/
/** Describe relation **/
$psql
	\d <relation_name>
/*********************/
/** Database export **/
/*********************/
pg_dump -U <new_user_name> <new_database> > dbexport.pgsql
pg_dump --no-owner <new_database> > dbexport.pgsql
/*********************/
/** Database import **/
/*********************/
psql -U <new_user_name> <new_database> < dbexport.pgsql

/******************************/
/** change owner of database **/ 
ALTER DATABASE <new_user_name> OWNER TO <new_user_name>;


