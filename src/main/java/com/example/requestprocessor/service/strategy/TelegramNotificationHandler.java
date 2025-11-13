package com.example.requestprocessor.service.strategy;

import com.example.requestprocessor.dto.NotificationType;
import com.example.requestprocessor.repository.NotificationOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class TelegramNotificationHandler extends AbstractNotificationHandler {
    public TelegramNotificationHandler(NotificationOutboxRepository repo, ObjectMapper mapper) {
        super(repo, mapper);
    }

    @Override
    protected String topic() {
        return "telegram-events";
    }

    @Override
    protected NotificationType type() {
        return NotificationType.TG_MESSAGE;
    }
}
