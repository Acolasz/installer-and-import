# Install

```shell
yum install -y nc
apt install -y netcat
```

# Usage

* ncat
* nc
* nmap

```shell
# Bind and listen for incoming connections
ncat --listen localhost 2333 > file_name
# The sending host connect to the receiving host and send the file
ncat receiving.host.com 5555 < file_name
```