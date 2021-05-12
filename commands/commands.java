/*************************************************************************************************/
/*************************************** Commands ************************************************/
/*************************************************************************************************/
/***********************************************************************/
/********************************* BUILD TOOL **************************/
/***********************************************************************/
/***********************************************************************/
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
https://www.whitesourcesoftware.com/free-developer-tools/blog/npm-vs-yarn-which-should-you-choose/
/** Cache **/
https://classic.yarnpkg.com/en/docs/cli/cache/
yarn cache list --pattern lodash
yarn cache dir
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
/** Add package **/
https://classic.yarnpkg.com/en/docs/cli/add/
yarn add package-name@1.2.3
/***********************************************************************/
/********************************* GIT *********************************/
/***********************************************************************/
git config --global user.email "oo@dorsum.eu"
git config --global user.name "Olivér"
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
git commit-m "Removed submodule "
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
/***********************************************************************/
/********************************* OPENSHIFT ***************************/
/***********************************************************************/
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
/***************/
/**** login ****/
oc login https://api.okd.dorsum.intra:6443
oc login
// oc 4.7.6 version
https://oauth-openshift.apps.okd.dorsum.intra/oauth/token/request
oc login --token=<ADuser_token> --server=https://api.okd.dorsum.intra:6443

/*****************/
/**** project ****/
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
/**** ETCD backup *****/
 https://docs.openshift.com/container-platform/4.5/backup_and_restore/backing-up-etcd.html#backing-up-etcd-data_backup-etcd
/****************************/
/**** Gracefull shutdown ****/
https://docs.openshift.com/container-platform/4.5/backup_and_restore/graceful-cluster-shutdown.html#graceful-shutdown-cluster
/**********************/
/**** UDP protokol ****/
https://docs.openshift.com/container-platform/4.4/networking/openshift_sdn/enabling-multicast.html
oc annotate netnamespace otp-bundle netnamespace.network.openshift.io/multicast-enabled=true
/***********************************************************************/
/********************************* UNIX ********************************/
/***********************************************************************/
/** service **/
journalctl -u docker.service
// service fájl
/etc/systemd/system/docker.service
// service parancsok
systemctl status docker.service
systemctl stop docker.service
systemctl start docker.service
/** könyvtárak méretét listázzuk **/
df -h
/** futó processeket **/
ps -ef
ps auxww
/** használt portok és ipcímek **/
netstat -anp
netstat -aun
/** CPU, memory usage **/
prstat -t
// adott userrel indított processeket mutatja
prstat -u <user>
// fájlokba keresi a greppelt szöveket és kilistázza a fájlt és a sort, ahol ez található
find ./ -name 'iq.log.2020-04-23*' -exec grep 'AppleNotificationHandler' {} \; -print
find ./install/installation-guide -name assetbasket -exec ls -l {} ;\ 

/** CURL **/
curl --insecure -i -d "clientIdentifierType=CIT_1004&brand=5000&IMPgIndx=1&IMPgRcCnt=500&clientId=22052&clientId=22345"  http://erstelnx:7001/ihs-adapter/restapi/clientlist/search
curl -X GET "http://dit19mpc014.dorsum.intra:9090/actuator" -H "Authorization: Basic X2NsYXZpc19wYl93czpfY2xhdmlzX3BiX3dz"
curl -X POST http://erstelnx:7001/ihs-adapter/restapi/investmentaccountdetails/search?investmentAccountNumberType=IAIT_1&clientIdentifierType=CIT_1004&brand=5000&IMPgRcCnt=5&IMPgIndx=1
/** WGET **/
wget --header="X-JFrog-Art-Api: AKCp5fUDpkASSHdmtosbg8id1s7fnrLtqVq22J3BT1fBu58P3gF3ayfqYe53PqESr7WsZgvFg" http://artifactory-nxt.dorsum.eu/artifactory/devops-installer/oracle/jdk/jdk-8u261-linux-x64.tar.gz
/** TAR **/
tar zxvf /tmp/install/wildfly-14.0.1.Final.tar.gz --strip 1 -C /opt/wildfly-14.0.1
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
/********************* Commands **********************/
/*****************************************************/
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

/***********************************************************************/
/********************************* SQL *********************************/
/***********************************************************************/
/********************* Select ************************/
/*****************************************************/
/** Intervallum check **/
/***********************/
										S----E 3.
			S---------------E 2.	
					[S]----------------------------[E]
												S----------------E 1.
SELECT *
FROM "AO_08B316_CLIENT_DETAIL" WHERE
("END_DATE" >= start AND "START_DATE" <= end) OR --2.
("START_DATE" <= end AND "END_DATE" >= start) OR --1.
("START_DATE" >= start AND "END_DATE" <= end) --3.

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
/********************************* Total Commander *********************/
/***********************************************************************/
http://dirty-joe.com/
/***********************************************************************/
/********************************* JENKINS *****************************/
/***********************************************************************/
/** Build number increase **/
/***************************/
Jenkins.instance.getItemByFullName("projectName/repositoryName/release%2F1.0").updateNextBuildNumber(2)
Jenkins.instance.getItemByFullName("projectName/repositoryName/master").updateNextBuildNumber(5)
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



















