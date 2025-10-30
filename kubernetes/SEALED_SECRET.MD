# Create Sealed secret

```shell
kubectl create secret generic secret-name --dry-run=client --from-literal=foo=bar -o yaml | \
kubeseal \
  --controller-name=sealed-secrets-controller \
  --controller-namespace=kube-system \
  --format yaml > mysealedsecret.yaml
```

```shell
kubectl get secrets/argocd-repo-icell-nexus-auth -n argocd -o yaml | \
kubeseal \
  --controller-name=sealed-secrets-controller \
  --controller-namespace=kube-system \
  --format yaml > argocd-repo-icell-nexus-auth.yaml
  
kubectl get secrets/argocd-repo-icell-gitlab-ssh -n argocd -o yaml | \
kubeseal \
  --controller-name=sealed-secrets-controller \
  --controller-namespace=kube-system \
  --format yaml > argocd-repo-icell-gitlab-ssh.yaml

kubectl get secrets/argocd-redis -n argocd -o yaml | \
kubeseal \
  --controller-name=sealed-secrets-controller \
  --controller-namespace=kube-system \
  --format yaml > argocd-redis.yaml

kubectl get secret/redis-replication -n redis-sentinel -o yaml | \
kubeseal \
  --controller-name=sealed-secrets-controller \
  --controller-namespace=kube-system \
  --format yaml > redis-cluster-sealed-secret.yaml
```
