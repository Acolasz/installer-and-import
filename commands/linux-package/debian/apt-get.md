# Usage

## apt-get (deb)

### added new repo

```shell
echo "deb https://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list;
```

```shell
add-apt-repository --list
apt search google-chrome
```

```shell
apt-get -y update      
apt-get -y install wget
apt-get clean
```

### packeage list

```shell
apt list --installed
# OR
dpkg -l
```

```shell
apt-cache search wget
apt-cache pkgnames wget
apt-cache showpkg wget
```

# Related article:

* [Ubuntu/Debian Linux apt-get package management cheat sheet][deb_cheatsheet]
* [APT Repository List (An Essential Guide)][repo_list]

[deb_cheatsheet]:<https://www.cyberciti.biz/tips/linux-debian-package-management-cheat-sheet.html>

[repo_list]:<https://linuxsimply.com/linux-basics/package-management/repository-list/apt/>
