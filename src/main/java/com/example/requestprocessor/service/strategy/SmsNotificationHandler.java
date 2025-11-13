package com.example.requestprocessor.service.strategy;

import com.example.requestprocessor.dto.NotificationType;
import com.example.requestprocessor.repository.NotificationOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationHandler extends AbstractNotificationHandler {
    public SmsNotificationHandler(NotificationOutboxRepository repo, ObjectMapper mapper) {
        super(repo, mapper);
    }

    @Override
    protected String topic() {
        return "sms-events";
    }

    @Override
    protected NotificationType type() {
        return NotificationType.SMS;
    }
}
