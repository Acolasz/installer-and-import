config:
  annotations: {}
  existingSecret: alertmanager-proxy-password
  cookieName: "alertmanager-proxy"
extraEnv:
- name: OAUTH2_PROXY_EMAIL_DOMAINS
  value: "*"
- name: OAUTH2_PROXY_COOKIE_DOMAINS
  value: "*.kktyn.hu"
- name: OAUTH2_PROXY_PROVIDER
  value: keycloak-oidc
- name: OAUTH2_PROXY_PROVIDER_DISPLAY_NAME
  value: "Keycloak"
- name: OAUTH2_PROXY_CODE_CHALLENGE_METHOD
  value: "S256"
- name: OAUTH2_PROXY_OIDC_ISSUER_URL
  value: https://keycloak.kktyn.hu/realms/emap
- name: OAUTH2_PROXY_REDIRECT_URL
  value: https://alertmanager.kktyn.hu/oauth2/callback
- name: OAUTH2_PROXY_UPSTREAMS
  value: http://prometheus-kube-prometheus-alertmanager:9093
- name: OAUTH2_PROXY_SCOPE
  value: openid profile email roles
- name: OAUTH2_PROXY_ALLOWED_ROLES
  value: "kktyn:viewer"
- name: OAUTH2_PROXY_COOKIE_REFRESH
  value: "1200s"
- name: OAUTH2_PROXY_COOKIE_EXPIRE
  value: "1800s"
customLabels: {}
authenticatedEmailsFile:
  enabled: false
service:
  type: ClusterIP
  portNumber: 80
  appProtocol: http
  annotations: {}
  # foo.io/bar: "true"
  targetPort: ""
resources:
  limits:
    cpu: 100m
    memory: 300Mi
  requests:
    cpu: 100m
    memory: 300Mi
# affinity: {}
tolerations: []
proxyVarsAsSecrets: true
deploymentAnnotations: {}
podAnnotations: {}
podLabels: {}
replicaCount: 1
revisionHistoryLimit: 10
strategy: {}
enableServiceLinks: true
httpScheme: http
initContainers:
  waitForRedis:
    enabled: false
htpasswdFile:
  enabled: false
redis:
  enabled: false
checkDeprecation: true
metrics:
  enabled: true
  serviceMonitor:
    enabled: true
    labels: {}
    annotations: {}
extraObjects: []
