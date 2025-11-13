package com.example.requestprocessor.service.strategy;

import com.example.requestprocessor.dto.NotificationType;
import com.example.requestprocessor.repository.NotificationOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationHandler extends AbstractNotificationHandler {
    public EmailNotificationHandler(NotificationOutboxRepository repo, ObjectMapper mapper) {
        super(repo, mapper);
    }

    @Override
    protected String topic() {
        return "email-events";
    }

    @Override
    protected NotificationType type() {
        return NotificationType.EMAIL;
    }
}
