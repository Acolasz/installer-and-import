# Install docker-desktop kubernetes cluster

Install from [kubernetes-docker-mac][install-kubertnes] or [k8_dashboard_on_docker_desktop][install-kubertnes-2]

```shell
kubectl culster-info
kubectl apply -f ./kubernetes-dashboard.yaml
kubectl proxy
```

## Browser following url

```shell
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/
```

### Create TOKEN

#### First serviceaccount (SA)
```shell
kubectl create serviceaccount cluster-admin-dashboard -n kubernetes-dashboard
kubectl create clusterrolebinding cluster-admin-dashboard \
    --clusterrole=cluster-admin \
    --serviceaccount=kubernetes-dashboard:cluster-admin-dashboard \
    -n kubernetes-dashboard
```

```shell
kubectl create token cluster-admin-dashboard -n kubernetes-dashboard
```

#### skip login

```shell
kubectl patch deployment kubernetes-dashboard -n kubernetes-dashboard --type 'json' -p '[{"op": "add", "path": "/spec/template/spec/containers/0/args/-", "value": "--enable-skip-login"}]'
```

[install-kubertnes]:<https://logz.io/blog/kubernetes-docker-mac/>
[install-kubertnes-2]:<https://willschenk.com/howto/2021/k8_dashboard_on_docker_desktop/>
