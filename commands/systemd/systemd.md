# Usage

## Command

```shell
systemctl list-units --type=service
```

```shell
/etc/systemd/system/docker.service
# reload service update
systemctl daemon-reload
# service parancsok
systemctl status docker.service
systemctl stop docker.service
systemctl start docker.service
# check status
systemctl is-active application.service
systemctl is-enabled application.service
# show service content
systemctl cat atd.service
```

# Related articles

* [How To Use Systemctl to Manage Systemd Services and Units][systemd_commands]

[systemd_commands]:<https://www.digitalocean.com/community/tutorials/how-to-use-systemctl-to-manage-systemd-services-and-units>
