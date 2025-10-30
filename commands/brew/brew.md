# Install

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

# Usage

* update: This updates Homebrew itself.
  ```shell
  brew update
  ```
* upgrade: This upgrades all individual packages and formulas.
  ```shell
  brew upgrade
  # only cask package
  brew upgrade --cask
  ```
* brew cleanup: This cleans up old versions of packages and frees up disk space.
  ```shell
  brew cleanup
  ```

