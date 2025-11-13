package com.example.requestprocessor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "outbox")
public class OutboxProperties {
    private int batchSize;
    private long delayMs;
}
