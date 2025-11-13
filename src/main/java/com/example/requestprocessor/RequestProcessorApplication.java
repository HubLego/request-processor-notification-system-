package com.example.requestprocessor;

import com.example.requestprocessor.config.OutboxProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(OutboxProperties.class)
public class RequestProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestProcessorApplication.class, args);
    }
}
