# Usage

```shell
# now reboot journal
journalctl -b 0
# since
journalctl --since "1 hour ago"
journalctl --since "2019-11-30 14:10:10" --until "2018-12-02 12:05:50"
#
journalctl -xe --since "2024-04-15 14:57:48"
journalctl -xe --since "2024-04-23"
journalctl -u jenkins.service
journalctl --unit jenkins --since "2016-10-30 18:17:16"
journalctl -xe --unit iscsid
```

## Flags:
* `-e` | `--pager-end` - jump to log end

# Related article:

* [Naplózás - journalctl használata Linuxon][naplozas_journalctl]

[naplozas_journalctl]:<https://linuxmint.hu/blog/2019/12/naplozas-journalctl-hasznalata-linuxon>
