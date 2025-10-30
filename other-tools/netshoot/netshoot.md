# Usage

```shell
kubectl run netshoot-oo --rm -i --tty --image nicolaka/netshoot -n default -- bash
kubectl debug -it log-viewer-stack-graylog-0 --image nicolaka/netshoot --target=graylog-server -n log-viewer-stack -- bash
kubectl debug -it jaeger-collector-84f459f44-rg9mx --image nicolaka/netshoot --target=jaeger-collector -n jaeger -- bash
kubectl debug -it jaeger-query-57ccb8478f-xfzrm --image nicolaka/netshoot --target=jaeger-query -n jaeger -- bash
kubectl debug -it ds-otel-collector-collector-558678dc-v9wfj --image nicolaka/netshoot --target=otc-container -n otel -- bash
kubectl debug -it dookug-document-service-84f5c7c988-dxkxg --image nicolaka/netshoot --target=otc-container -n dookug-prd -- bash
```
