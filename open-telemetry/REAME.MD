# Usage

## OpenTracing (Jaeger stack)

> OpenTracing is an old architecture before OpenTelemetry.
> This is the manifests collection [jaeger-v1.62.0][jaeger_old]. It contains jaeger-agent and next versions does not
> exist [github 4739][github_issue_jaeger_agent_deprecated]

# Collector config.yaml file

* Validate collector config file [otelbin.io][collector_config_file_validation]
* Exporters:
  * debug

# Create custom Opentelemetry collector

* Install [builder CLI][build_custom_collector]
    * `go install go.opentelemetry.io/collector/cmd/builder@latest`
    * `builder --help`

[collector_config_file_validation]:<https://www.otelbin.io/>

[build_custom_collector]:<https://betterstack.com/community/guides/observability/custom-opentelemetry-collector/>

[jaeger_old]:<./jaeger/jaeger-v1.62.0-manifests.yaml>

[github_issue_jaeger_agent_deprecated]:<https://github.com/jaegertracing/jaeger/issues/4739>