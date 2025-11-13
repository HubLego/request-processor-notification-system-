package com.example.requestprocessor.service.strategy;

import com.example.requestprocessor.dto.NotificationRequestDto;
import com.example.requestprocessor.dto.NotificationType;
import com.example.requestprocessor.model.NotificationOutbox;
import com.example.requestprocessor.repository.NotificationOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractNotificationHandler {

    private final NotificationOutboxRepository outboxRepository;
    private final ObjectMapper mapper;

    protected abstract String topic();

    protected abstract NotificationType type();

    public void handle(NotificationRequestDto request) {
        String key = UUID.randomUUID().toString();
        String body;
        try {
            body = mapper.writeValueAsString(Map.of("type", type().name(), "message", request.message()));
        } catch (Exception e) {
            throw new RuntimeException("JSON serialize error", e);
        }
        NotificationOutbox outbox = NotificationOutbox.builder()
                .topic(topic())
                .key(key)
                .value(body)
                .sent(false)
                .attempt(1)
                .build();
        outboxRepository.save(outbox);
        System.out.printf("Подготовлено сообщение для отправки. Key: %s, Payload: %s, topic: %s%n", key, body, topic());
    }

    public NotificationType getType() {
        return type();
    }
}
