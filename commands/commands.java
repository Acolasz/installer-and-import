/*************************************************************************************************/
/*************************************** Commands ************************************************/
/*************************************************************************************************/
/***********************************************************************/
/********************************* GIT *********************************/
/***********************************************************************/
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
git commit --allow-empty -m "test empty commit" && git push -u origin <brancg>
/***********************************************************************/
/********************************* OPENSHIFT ***************************/
/***********************************************************************/
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
						name: <configmap_name>







