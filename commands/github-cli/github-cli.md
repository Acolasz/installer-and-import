# Install

```shell
brew install gh
gh --version
```

# Usage

## GitHub CLI configuration `gh config`

```shell
gh config list
```

### Set Git [Protocol SSH][gh_config_ssh]

```shell
gh config set git_protocol ssh
```

## Repository

```shell
gh repo clone <account>/<repository_name>
```

[gh_config_ssh]:<https://github.com/cli/cli/issues/1425>
