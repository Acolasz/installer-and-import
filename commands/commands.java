/*************************************************************************************************/
/*************************************** Commands ************************************************/
/*************************************************************************************************/
/***********************************************************************/
/********************************* GIT *********************************/
/***********************************************************************/
/********************* SUBMODULE *********************/
/*****************************************************/
// Change branch
git submodule add --name installation-guide -b release/20.10.01 https://stash.dorsum.eu/scm/otp/clavis-nxt-installation-guide-otp.git ./install/clavis-nxt-installation-guide/
git submodule init
// Submodule remote refresh
git submodule update --init
// if there are nested submodules:
git submodule update --init --recursive
// pull all changes for the submodules
git submodule update --remote
/* submodule remove */
delete .gitmodules file
        .git/config
git add .gitmodules
git rm --cached path_to_submodule
rm -rf .git/modules/clavis-nxt-installation-guide-otp/
git submodule deinit ./install/clavis-nxt-installation-guide-otp/
git rm ./install/clavis-nxt-installation-guide-otp/
// remote commit !!
git commit-m "Removed submodule "
rm -rf .git/modules/installation-guide/
