# Usage

## yum (rpm)

### added new repo

```shell
cat << EOL >> /etc/yum.repos.d/google-chrome.repo
[google-chrome]
name=google-chrome
baseurl=http://dl.google.com/linux/chrome/rpm/stable/x86_64
enabled=1
gpgcheck=1
gpgkey=https://dl.google.com/linux/linux_signing_key.pub
EOL
```

/etc/yum.repos.d/google-chrome.repo

```shell
yum repolist
yum search google-chrome
```

```shell
yum -y update      
yum -y install \
  java-11-openjdk \
  https://yum.oracle.com/repo/OracleLinux/OL8/appstream/x86_64/getPackage/xorg-x11-server-Xvfb-1.20.11-17.el8.x86_64.rpm
yum clean all
```

### el7

### el8


# Related article:

* [YUM COMMAND
  CHEAT SHEET
  for Red Hat Enterprise Linux][yum_cheatsheet]

[yum_cheatsheet]:<https://access.redhat.com/sites/default/files/attachments/rh_yum_cheatsheet_1214_jcs_print-1.pdf>
