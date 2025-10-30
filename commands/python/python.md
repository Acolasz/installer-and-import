# Install tkinter

```shell
brew install python-tk
```

# Install

## from tgz in DEBIAN

```shell
RUN cd /tmp && \
    curl -o Python-3.12.2.tgz https://www.python.org/ftp/python/3.12.2/Python-3.12.2.tgz && \
    tar xzf ./Python-3.12.2.tgz && \
    cd /tmp/Python-3.12.2 &&  \
    ./configure && \
    make -j 4 && \
    make altinstall && \
    python3.12 -m ensurepip --upgrade

RUN python3.12 -V && \
    pip3.12 -V
```

## MacOS

```shell
brew install python@3.12
brew install python@3.11
brew install python@3.9
```

### Check version

```shell
python3.12 -V
python3.11 -V
python3.9 -V
```

# Usage virtualenv

```shell
python3.9 -m venv ~/.python/3.9-ansible
python3.11 -m venv ~/.python/3.11-ansible
python3.12 -m venv ~/.python/3.12-ansible
python3.11 -m venv ~/.python/pre-commit
python3.11 -m venv ~/.python/3.11-emap
```

## Activate venv

```shell
. ~/.python/3.9-ansible/bin/activate
. ~/.python/3.11-ansible/bin/activate
. ~/.python/3.12-ansible/bin/activate
. ~/.python/pre-commit/bin/activate
. ~/.python/3.11-emap/bin/activate
```

## pip install

```shell
python -m ensurepip --upgrade
# OR
curl https://bootstrap.pypa.io/get-pip.py
python get-pip.py
```

## pip upgrade

```shell
python -m pip install --upgrade wheel
python -m pip install --upgrade pip
```

```shell
python -m pip install -r ./commands/python/pre-commit/requirements.txt
python -m pip install --upgrade -r ./commands/python/3.9-ansible/requirements.txt
python -m pip install --upgrade -r ./commands/python/3.9-ansible/requirements.txt --use-pep517
python -m pip install -r ./commands/python/3.11-ansible/requirements.txt
```

## Deactivate current venv

```shell
deactivate
```

## Prompt setup

* [setup .p10k.zsh][promt_powerlevel10k]
* ```shell
  typeset -g POWERLEVEL9K_VIRTUALENV_SHOW_PYTHON_VERSION=true
  typeset -g POWERLEVEL9K_VIRTUALENV_SHOW_WITH_PYENV=true
  typeset -g POWERLEVEL9K_VIRTUALENV_GENERIC_NAMES=(virtualenv venv .venv env)
  ```

# Install/Uninstall packages

> Usage [pip][pip_md] package controller tool

```shell
# Install the latest version (does not upgrade)
python -m pip install <package>
python -m pip uninstall <package> -y
# or
python -m pip install <package1> <package2>
python -m pip uninstall <package1> <package2> -y
# Upgrade to the latest stable version
python -m pip install --upgrade <package>
python -m pip uninstall --upgrade <package> -y
# Upgrade to the latest version even if it is a pre-release
python -m pip install --upgrade --pre <package>
python -m pip uninstall --upgrade --pre <package> -y
# Install a specific version
python -m pip install <package>==<version>
python -m pip uninstall <package>==<version> -y
```

## from requirements.txt

```shell
# create requirements.txt
python -m pip freeze > ./requirements.txt
```

```shell
python -m pip install -r requirements.txt 
python -m pip uninstall -r requirements.txt -y
```

# Package list

```shell
python -m pip list
```

[promt_powerlevel10k]:<https://github.com/romkatv/powerlevel10k/issues/532>

[pip_md]:<./pip.md>
