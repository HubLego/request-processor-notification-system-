package com.example.requestprocessor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
    @Bean
    NewTopic smsEvents() {
        return TopicBuilder.name("sms-events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic emailEvents() {
        return TopicBuilder.name("email-events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic pushEvents() {
        return TopicBuilder.name("push-events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic telegramEvents() {
        return TopicBuilder.name("telegram-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
