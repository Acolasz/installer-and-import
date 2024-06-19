# Usage

# Troubleshouting

## A namespace is stuck in the Terminating state

```shell
kubectl proxy
```

> new terminal

```shell
kubectl get namespace <terminating-namespace> -o json >tmp.json
```

> > modify tmp.json

```json
{
  "apiVersion": "v1",
  "kind": "Namespace",
  "metadata": {
    "creationTimestamp": "2018-11-19T18:48:30Z",
    "deletionTimestamp": "2018-11-19T18:59:36Z",
    "name": "<terminating-namespace>",
    "resourceVersion": "1385077",
    "selfLink": "/api/v1/namespaces/<terminating-namespace>",
    "uid": "b50c9ea4-ec2b-11e8-a0be-fa163eeb47a5"
  },
  "spec": {},
  "status": {
    "phase": "Terminating"
  }
}
```

```shell
curl -k -H "Content-Type: application/json" -X PUT --data-binary @tmp.json http://127.0.0.1:8001/api/v1/namespaces/<terminating-namespace>/finalize
```

# Related article:

* [A namespace is stuck in the Terminating state][terminating_state]

[terminating_state]:<https://www.ibm.com/docs/en/cloud-private/3.2.x?topic=console-namespace-is-stuck-in-terminating-state>
