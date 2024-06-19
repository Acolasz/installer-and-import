# Usage

## List

```shell
kubectl api-resources --api-group=rbac.authorization.k8s.io
# Verbs
kubectl api-resources --api-group=rbac.authorization.k8s.io -o wide
```

## ClusterRoleBinding

```shell
kubectl get clusterrolebindings
```

## ClusterRole

```shell
kubectl get clusterroles
```

## RoleBinding

```shell
kubectl get rolebindings
```

## Role

```shell
kubectl get roles
```
