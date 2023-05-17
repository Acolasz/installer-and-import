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
/********************* .NPMRC ************************/
/*****************************************************/
/** locations .npmrc **/
yarn install v1.22.10
14:06:55  verbose 0.389 Checking for configuration file "/var/jenkins-nxt-prd/workspace/MYW_mw-web_diofa_1.70.1-nxt-test/.npmrc".
14:06:55  verbose 0.39 Checking for configuration file "/home/DORSUM/jenkins-prd/.npmrc".
14:06:55  verbose 0.39 Found configuration file "/home/DORSUM/jenkins-prd/.npmrc".
14:06:55  verbose 0.39 Checking for configuration file "/opt/nodejs-8.11.3/etc/npmrc".
14:06:55  verbose 0.391 Checking for configuration file "/var/jenkins-nxt-prd/workspace/MYW_mw-web_diofa_1.70.1-nxt-test/.npmrc".
14:06:55  verbose 0.391 Checking for configuration file "/var/jenkins-nxt-prd/workspace/.npmrc".
14:06:55  verbose 0.391 Checking for configuration file "/var/jenkins-nxt-prd/.npmrc".
14:06:55  verbose 0.391 Checking for configuration file "/var/.npmrc".
/*****************************************************/
/********************* Yarn **************************/
/*****************************************************/
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
/***********************************************************************/
/********************************* GIT *********************************/
/***********************************************************************/
git config --global user.email "oo@dorsum.eu"
git config --global user.name "Olivér"
/*****************************************************/
/********************* Verbose ***********************/
/*****************************************************/
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
git add .
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
helm lint -f ./default-simple-service-override.yaml.yaml ./default-simple-service
helm template -f ./default-simple-service-override.yaml.yaml ./default-simple-service
helm package ./default-simple-service
helm install -f myvalues.yaml -f override.yaml  myredis ./redis
helm install wmp-srv-sarsn -f ./default-simple-service-override.yaml.yaml ./default-simple-service-1.0.0.tgz
helm repo update helm --username oo --password AP3rd15kegHHQSagiuX4aLTuw4R
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
/****************************/
/**** Project permission ****/
// for group
oc adm policy add-role-to-group -h
oc adm policy add-role-to-group <role> <group_name> --rolebinding-name='<rolebinding_name>' -n <project>
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
oc create configmap shadow-datasources-properties \
    --from-file=./install/installation-guide/localhost/properties_files/shadow.properties
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
oc annotate netnamespace otp-bundle netnamespace.network.openshift.io/multicast-enabled=true
/*****************************/
/**** Remove evicted pods ****/
/* @see https://sachsenhofer.io/how-to-remove-evicted-pods-in-kubernetes-openshift/ */
oc get pods --all-namespaces -o json | jq '.items[] | select(.status.reason!=null) | select(.status.reason | contains("Evicted")) | "oc delete pods \(.metadata.name) -n \(.metadata.namespace)"' | xargs -n 1 bash -c
oc get pods --all-namespaces -o json | jq '.items[] | select(.status.reason!=null) | select(.status.reason | contains("Running")) | xargs -n 1 bash -c
/*****************************/
/**** Delete Node steps ******/
/* @see https://www.techbeatly.com/openshift-cluster-how-to-drain-or-evacuate-a-node-for-maintenance/ */
sudo podman images
oc get nodes | grep compute-102
// Mark as Unschedulable
oc adm cordon okd-w3.okd.dorsum.intra
// Mark as Schedulable
oc adm uncordon okd-w3.okd.dorsum.intra
// drain node
oc adm drain okd-w3.okd.dorsum.intra
oc adm drain okd-w3.okd.dorsum.intra --delete-local-data=false --ignore-daemonsets=false  --grace-period=120 --force=true
oc adm drain okd-w3.okd.dorsum.intra --delete-local-data=false --ignore-daemonsets=false  --grace-period=120 --pod-selector='' --force=true
oc adm drain okd-w3.okd.dorsum.intra --delete-local-data=false --ignore-daemonsets=false  --grace-period=120 --selector='' --force=true
// get/describe node
oc get node okd-w3.okd.dorsum.intra
oc describe node okd-w3.okd.dorsum.intra
// top node
oc adm top nodes
/*****************************/
/**** open Node OC command ***/
oc debug node/okd-w1.okd.dorsum.intra
/***********************************************************************/
/********************************* UNIX ********************************/
/***********************************************************************/
/** pacakge update **/
apt --only-upgrade install git
apt --only-upgrade install git-man
// Check
apt list git
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
/********************************* MAVEN *******************************/
/***********************************************************************/
/********************* Lifecycle *********************/
/*****************************************************/
https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
/*****************************************************/
/** Skip test **/
// Plugin maven-surefire-plugin
mvn -U clean package -DskipTests
/** Skip Integration test **/
// Plugin maven-failsafe-plugin
mvn -U clean package -DskipITs
/** Custom settings.xml **/
mvn -U clean package --global-settings ./path/to/location/global-settings.xml --settings ./path/to/location/local-settings.xml
/** Deploy file to artifactory **/
mvn deploy:deploy-file -DrepositoryId="dsp" -Durl="https://pkgs.dev.azure.com/MPZRT/dsp-devops/_packaging/dsp/maven/v1" -DgroupId="eu.dorsum.posta" -DartifactId="posta-srv-kwr-ejb" -Dversion="1.1.0" -Dclassifier="client" -Dpackaging="jar" -Dfile="./posta-srv-kwr-ejb-2.2.5-2-client.jar"
mvn deploy:deploy-file -Dpackaging="pom" -DrepositoryId="drsm" -Durl="https://pkgs.dev.azure.com/Dorsum/Posta/_packaging/drsm/maven/v1" -DgroupId="eu.dorsum.cm" -DartifactId="azure-pipeline-mvn-parent" -Dversion="1.0.0" -Dfile="./pom.xml"
/** Sonar **/
mvn sonar:sonar -Dsonar.projectKey=eu.dorsum.otp.java.clavis-iq-otp:clavis-iq-otp:int -Dsonar.host.url=https://sonar.dorsum.eu -Dsonar.login=89ebc802c6592e7ba3c2b994e21473b32305d5ce

/** plugin help / descriptor **/
mvn help:describe -Dplugin=eu.dorsum.cm.java.git-changelog:git-changelog-maven-plugin
mvn git-changelog-maven-plugin:help -Ddetail=true
mvn eu.dorsum.cm.java.git-changelog:git-changelog-maven-plugin:help -Ddetail=true
mvn git-changelog-maven-plugin:help -Ddetail=true -Dgoal=git-changelog
mvn eu.dorsum.cm.java.git-changelog:git-changelog-maven-plugin:help -Ddetail=true -Dgoal=git-changelog

/** effective-pom **/
// pass properties value and write content all pom.xml
mvn help:effective-pom
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
/********************************* JAD *********************************/
/***********************************************************************/
/** CLI **/
http://www.javadecompilers.com/jad
// command
jad -o -r -sjava -dsrc ./**/*.class
/** GUI **/
https://github-releases.githubusercontent.com/32844456/f5daf300-272d-11ea-93dc-b7f9005f21d0?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20210818%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210818T072439Z&X-Amz-Expires=300&X-Amz-Signature=21bd02bef81dd528a548cc7f03c20a2a3c47546673cc804e6e1a51cab82ffb0f&X-Amz-SignedHeaders=host&actor_id=53154839&key_id=0&repo_id=32844456&response-content-disposition=attachment%3B%20filename%3Djd-gui-windows-1.6.6.zip&response-content-type=application%2Foctet-stream
/***********************************************************************/
/********************************* JAVA ********************************/
/***********************************************************************/
/*************/
/** Keytool **/
/*************/
// List
keytool -v -list -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts"
keytool -list -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts" -alias erste.hu
// Print
keytool -printcert -file "d:\workspace\stash-prd\ERSTE\tmp\ersteca.cer"
// Import
keytool -importcert -file "d:\workspace\stash-prd\ERSTE\tmp\ersteca.cer" -alias erste.hu -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts" -storepass changeit 
// delete
keytool -delete -alias erste.hu -keystore "d:\Portable\Java\jdk1.8.0_172\jre\lib\security\cacerts" -storepass changeit
/***********************************/
/** WSImport WSDL to Java Objects **/
/***********************************/
d:\Portable\Java\jdk1.6.0_24\bin\wsimport.exe -p eu.dorsum.erste.cwa.web.filenet.store https://_clavis_filenet_ws:12345678@ebhfat.erste.hu:476/fnapiws/StoreV1?wsdl
d:\Portable\Java\jdk1.6.0_24\bin\wsimport.exe https://_clavis_filenet_ws:12345678@ebhfat.erste.hu:476/fnapiws/StoreV1?wsdl
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


