# Usage

## Cert check (.pfx, .pem, ...)

```shell
openssl x509 -noout -text -in ~/cert.pfx
```

## Check TLS (SSL) - SMTP (StartTLS)

```shell
openssl s_client -connect 10.68.254.172:587
openssl s_client -connect 10.68.254.172:587 -starttls smtp -crlf
```
