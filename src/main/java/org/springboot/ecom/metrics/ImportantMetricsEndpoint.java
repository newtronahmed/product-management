package org.springboot.ecom.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "importantMetrics")
public class ImportantMetricsEndpoint {
    private final MeterRegistry meterRegistry;

    public ImportantMetricsEndpoint(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @ReadOperation
    public Map<String, Object> importantMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        // JVM Metrics
        addMetricIfAvailable(metrics, "jvm.memory.used");
        addMetricIfAvailable(metrics, "jvm.memory.max");
        addMetricIfAvailable(metrics, "jvm.threads.live");
        addMetricIfAvailable(metrics, "jvm.gc.memory.allocated");
        addMetricIfAvailable(metrics, "jvm.gc.memory.promoted");

        // System Metrics
        addMetricIfAvailable(metrics, "system.cpu.usage");
        addMetricIfAvailable(metrics, "system.cpu.count");
        addMetricIfAvailable(metrics, "system.load.average.1m");

        // Process Metrics
        addMetricIfAvailable(metrics, "process.cpu.usage");
        addMetricIfAvailable(metrics, "process.uptime");

        // HTTP Metrics
        addMetricIfAvailable(metrics, "http.server.requests");

        // Tomcat Metrics (if using Tomcat)
        addMetricIfAvailable(metrics, "tomcat.sessions.active.current");
        addMetricIfAvailable(metrics, "tomcat.threads.current");

        // Add custom application-specific metrics here

        return metrics;
    }

    private void addMetricIfAvailable(Map<String, Object> metrics, String metricName) {
        Gauge gauge = meterRegistry.find(metricName).gauge();
        if (gauge != null) {
            metrics.put(metricName, gauge.value());
        }
    }
}