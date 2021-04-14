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
git commit --allow-empty -m "test empty commit"
