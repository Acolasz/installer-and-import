# zsh install

```shell
brew install zsh
```

# oh-my-zsh install

> * [oh-my-zsh wiki][ohmyzsh_wiki]

```shell
sh -c "$(curl -fsSL https://install.ohmyz.sh)"
```

## oh-my-zsh setup

### Plugins

> * [oh-my-zsh plugin setup][ohmyzsh_setup_plugin]
> * [oh-my-zsh plugin setup extra][ohmyzsh_setup_plugin_extra]

* autosuggesions plugin
    * `git clone https://github.com/zsh-users/zsh-autosuggestions.git ${ZSH_CUSTOM}/plugins/zsh-autosuggestions`
* zsh-autocomplete plugin
    * `git clone --depth 1 -- https://github.com/marlonrichert/zsh-autocomplete.git ${ZSH_CUSTOM}/plugins/zsh-autocomplete`
* zsh-fast-syntax-highlighting plugin
    * `git clone https://github.com/zdharma-continuum/fast-syntax-highlighting.git ${ZSH_CUSTOM}/plugins/fast-syntax-highlighting`
* zsh-syntax-highlighting plugin
    * `git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM}/plugins/zsh-syntax-highlighting`

### Themes

* [kube-zsh-theme][ohmyzsh_setup_theme_kube]
  * `git clone https://github.com/tigerjz32/kube-zsh-theme.git/ ${ZSH_CUSTOM}/themes/kube-zsh-theme`
  * ZSH_THEME="robbyrussell"

### fancy feature

[fancy - PowerLevel10K][ohmyzsh_fancy_powerlevel10k]

## oh-my-zsh usage

> * [multiple][ohmyzsh_usage_multiple] iTerm tabs - `COMMAND + SHIFT + I`

[ohmyzsh_wiki]:<https://github.com/ohmyzsh/ohmyzsh/wiki>

[ohmyzsh_setup_plugin]:<https://sourabhbajaj.com/mac-setup/iTerm/zsh.html>

[ohmyzsh_setup_plugin_extra]:<https://dev.to/zeromeroz/setting-up-zsh-and-oh-my-zhs-with-autocomplete-plugins-1nml>

[ohmyzsh_setup_theme_kube]:<https://github.com/tigerjz32/kube-zsh-theme>

[ohmyzsh_usage_multiple]:<https://medium.com/@wendymak/sending-commands-to-multiple-iterm-tabs-f18b66dc5387>

[ohmyzsh_fancy_powerlevel10k]:<https://medium.com/@shivam1/make-your-terminal-beautiful-and-fast-with-zsh-shell-and-powerlevel10k-6484461c6efb>
