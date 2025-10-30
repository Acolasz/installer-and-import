# Usage

```shell
echo hostname-{1..3}.kktyin.hu | xargs -n1 \
  | xargs -I% sshpass -p valami ssh-copy-id -f -i ~/.ssh/id_rsa oliver.ocsko@%

echo hubphq-hugo-amq-d001 | xargs -n1 \
  | xargs -I% sshpass -p valami ssh-copy-id -f -i ~/.ssh/id_rsa oliver.ocsko@%
```

# Copy from remote host with    `scp`

```shell
scp oliver.ocsko@<remote_ip>:/home/ocsko.oliver/valami.zip ./<local_path>/
```
