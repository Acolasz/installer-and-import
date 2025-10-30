# Usage

```shell
tcpdump -i any -n udp \
and dst 10.10.32.5 \
and src 10.10.36.250
```

```shell
tcpdump -i any dst 34.149.236.64 and src 10.10.101.114
tcpdump -i any dst 10.10.101.114 and src 34.149.236.64
```

## check SMTP

```shell
tcpdump -i any -A host 10.68.254.172 and port 587
tcpdump -vv -i any -A host 10.68.254.172 and port 587
tcpdump -vv -nn -i any -A host 10.68.254.172 and port 587
tcpdump -i any -A host 10.68.254.172 and port 587
```
