# Install

```shell
gh repo clone nvm-sh/nvm /Users/oliver.ocsko/.nvm
git checkout v0.40.1
```

> .zshrc
> ```shell
> export NVM_DIR="$HOME/.nvm"
> [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
> [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"
> ```
> 
> ```shell
> . /Users/oliver.ocsko/.zshrc
> ```

## Windows

Download [windows][download_windows]

### Install

[install for windows][install_windows]

```shell
c:\Portable\nvm\nvm-1.1.7\nvm\
```
## Check version

```shell
nvm version
```

nvm list
nvm use <nodejs_version>
nvm use 10.15.1
/** Transitive Dependencies **/
https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Transitive_Dependencies

# Usage

## List available applications

```shell
nvm list
#
            N/A
iojs -> N/A (default)
node -> stable (-> N/A) (default)
unstable -> N/A (default)
```

## Install node versions

```shell
# List remote node versions
nvm ls-remote
# List local installed node versions
nvm ls
# Install node version
nvm install --lts=<lts_version_name>
nvm install --lts=jod

nvm install <nodejs_version>
nvm install 22.14.0
```

## Check node version

```shell
node --version
v22.14.0
npm --version
10.9.2
```

[download_windows]:<https://github.com/coreybutler/nvm-windows/releases>

[install_windows]:<https://stackoverflow.com/questions/25654234/node-version-manager-nvm-on-windows>
