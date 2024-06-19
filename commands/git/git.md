# Usage

## Global config

```shell
git config --global user.email "olasz@kukutyin.hu"
git config --global user.name "Olivér"
```

## gitignore

> [.gitignore][gitignore]

## gitattributes

> [.gitattributes][gitattributes]

```shell
# Attribute sets
git ls-files | git check-attr -a --stdin
```

## Environment Variable

> [Environment Variables][environment_variables]

### Committing

```shell
GIT_AUTHOR_NAME is the human-readable name in the “author” field.
GIT_AUTHOR_EMAIL is the email for the “author” field.
GIT_AUTHOR_DATE is the timestamp used for the “author” field.
GIT_COMMITTER_NAME sets the human name for the “committer” field.
```

### Verbose

```shell
# Linux
export GIT_TRACE_PACKET=1
export GIT_TRACE=1
export GIT_CURL_VERBOSE=1
```

## Git Push

> error: RPC failed; HTTP 500 curl 22 The requested URL returned error: 500
> send-pack: unexpected disconnect while reading sideband packet
> Writing objects: 100% (50/50), 77.67 MiB | 25.22 MiB/s, done.
> Total 50 (delta 7), reused 0 (delta 0), pack-reused 0
> fatal: the remote end hung up unexpectedly

```shell
git config --global http.postBuffer 500M
git config --global http.maxRequestBuffer 100M
```

## Git Copy

```shell
git checkout <otherbranch> <path/to/file>
```

## Git Submodule

```shell
# Change branch
git submodule add --name kukutyin-submodule -b
release/1.0.0 https://stash.kukutyin.hu/scm/kukutyin/kukutyin-submodule.git
./app-install/kukutyin-submodule/
git submodule init
# Submodule remote refresh
git submodule update --init
# if there are nested submodules:
git submodule update --init --recursive
# pull all changes for the submodules
git submodule update --remote
````

> submodule remove

```shell 
delete .gitmodules file
delete submodul section from .git/config file
git add .gitmodules
git rm --cached .git/modules/kukutyin-submodule/
rm -rf .git/modules/kukutyin-submodule/
git submodule deinit ./app-install/kukutyin-submodule/
git rm ./app-install/kukutyin-submodule/
# remote commit !!
git commit -m "Removed submodule "
rm -rf .git/modules/kukutyin-submodule/
```

## Git Reset

```shell
# before last commit
git reset --soft HEAD~1
git reset --mixed HEAD~1
git reset --hard HEAD~1
```

## Git Commit

### Merge revert commit

```shell
# álljunk, amibe mergeltük ágra,
# -m 1|2 flaghez trartozó számok a revertálni kívánt commit őseit jelenti
# válasszuk az 1-est és felugró ablak után ENTER és lépjünk ki
# Létrejött Revert commit-ot ellenőrizzük, hogy a megfelelő változtatások vannak e benne
# majd push
git revert <commit_hash> -m 1
```

### Empty commit

```shell
git commit --allow-empty -m "test empty commit" && git push -u origin <branch>
```

## Windows credetial

```shell
# remove:
# - passwd file ins sourcetre
# - windows credentials bitbucket.dorsum.eu
git config --global credential.helperselector.selected wincred
```

## Git Remote

### Remote ist

```shell
git remote -v
```

## Git Update .git cache

```shell
git rm -r --cached .
git clean -fxd
git restore .
git add .
```

> [empty objects][empty_objects]

```shell
git fsck --full
rm .git/objects/8b/61d0135d3195966b443f6c73fb68466264c68e
git update-ref HEAD 9f0abf890b113a287e10d56b66dbab66adc1662d
```

## Discard

### Staged

```shell
# Staged files to unstacked files
git restore --staged .
```

### Unstaged

```shell
# Tracked files
git restore .
# or
git reset --hard
# Untracked files
git clean -f
```

[gitignore]:<https://github.com/github/gitignore>

[gitattributes]:<https://github.com/alexkaratarakis/gitattributes/blob/master/.gitattributes>

[environment_variables]:<https://git-scm.com/book/en/v2/Git-Internals-Environment-Variables>

[empty_objects]:<https://stackoverflow.com/questions/11706215/how-can-i-fix-the-git-error-object-file-is-empty>
