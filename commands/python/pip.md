# Install packages

> [pip install][pip_install_all]

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
pip install --no-cache ansible==2.10.7 bump2version jsondiff lxml
# from archive
python -m pip install [options] <archive url/path> 
```

## from requirements.txt

```shell
# create requirements.txt
pip freeze > /tmp/requirements.txt
```

```shell
pip install -r requirements.txt
pip install --upgrade -r requirements.txt
```

# Package list

```shell
pip list
```

## Dependency tree

> [Show Python Package Dependencies][pip_dependency_tree]

### Install

```shell
pip install pipdeptree
```

### Usage

```shell
pipdeptree -fl
pipdeptree -r -p RPi.GPIO
```

[pip_install_all]:<https://pip.pypa.io/en/stable/cli/pip_install/>

[pip_dependency_tree]:<https://www.shellhacks.com/pip-show-python-package-dependencies/>
