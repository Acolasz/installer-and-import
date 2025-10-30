# Optimizing DNS Resolution in Kubernetes

> [Optimizing DNS Resolution][optimizing_dns_resolution]

## ndots:5

```yaml
# in the same namespace
HOST: "service-name"
URL: "http://service-name"
```

```yaml
# from other namespace
HOST: "service-name.other-namespace.svc.cluster.local."
URL: "http://service-name.other-namespace.svc.cluster.local.:8080"
```

```yaml
# external url
URL: "https://google.com."
```

# Related article

* [Doggo - DNS client][doggo_dns_resolution_app]

[optimizing_dns_resolution]:<https://medium.com/@GiteshWadhwa/optimizing-dns-resolution-in-kubernetes-best-practices-for-coredns-performance-e3f6ed041bbb#:~:text=Optimizing%20DNS%20resolution%20in%20Kubernetes%20by%20using%20FQDNs,minimize%20DNS%20traffic%20and%20improve%20your%20CoreDNS%20performance.>

[doggo_dns_resolution_app]:<https://doggo.mrkaran.dev/docs/>
