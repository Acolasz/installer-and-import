# Usage

# NFS

> * [storage/persistent-volumes/nfs][nfs_sample]
> * [Persistent storage for container logging using Fluent Bit][nfs_fluentbit]

## Delete

```shell
kubectl patch pv pvc-5ac9e4fe-4f75-458b-900a-9e795fe1c2ff -p '{"metadata": {"finalizers": null}}'
kubectl delete pv pvc-5ac9e4fe-4f75-458b-900a-9e795fe1c2ff --grace-period=0 --force
```

[nfs_sample]:<https://kubernetes.io/docs/concepts/storage/persistent-volumes/#persistent-volumes>

[nfs_fluentbit]:<https://aws.amazon.com/blogs/storage/persistent-storage-for-container-logging-using-fluent-bit-and-amazon-efs/>