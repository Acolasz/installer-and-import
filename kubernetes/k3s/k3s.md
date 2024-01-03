# K3s

## Install

```shell
curl -sfL https://get.k3s.io | sh -
curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--tls-san x.x.x.x" sh -s -
```

* K3S_TOKEN: `cat /var/lib/rancher/k3s/server/node-token`
* KUBECONFIG: `cat /etc/rancher/k3s/k3s.yaml`

# Related articles

* K3s - [Quick-Start Guide][quick_start]
* K3s - issue [Remote kubectl x509: certificate is valid for 127.0.0.1][issue_quick_start]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[quick_start]:<https://docs.k3s.io/quick-start>

[issue_quick_start]:<https://github.com/k3s-io/k3s/issues/1381>
