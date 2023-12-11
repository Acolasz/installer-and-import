# Usage

##

```shell
k get secret <secret_name> --template='{{index .data "KEY_NAME"}}' -n <NAMESPACE> | base64 -d
```