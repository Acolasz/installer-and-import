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
```

# Usage virtualenv

```shell
python3 -m venv ~/.python/valami/venv
```

## Prompt setup

* [setup .p10k.zsh][promt_powerlevel10k]
* ```shell
  typeset -g POWERLEVEL9K_VIRTUALENV_SHOW_PYTHON_VERSION=true
  typeset -g POWERLEVEL9K_VIRTUALENV_SHOW_WITH_PYENV=true
  typeset -g POWERLEVEL9K_VIRTUALENV_GENERIC_NAMES=(virtualenv venv .venv env)
  ```

# Install packages

```shell
# Install the latest version (does not upgrade)
pip install <package>
# or
pip install <package1> \
    <package2>
# Upgrade to the latest stable version
pip install --upgrade <package>
# Upgrade to the latest version even if it is a pre-release
pip install --upgrade --pre <package>
# Install a specific version
pip install <package>==<version>
```

## from requirements.txt

```shell
# create requirements.txt
pip3.12 freeze > /tmp/requirements.txt
```

```shell
pip3.12 install -r requirements.txt
```

[promt_powerlevel10k]:<https://github.com/romkatv/powerlevel10k/issues/532>
