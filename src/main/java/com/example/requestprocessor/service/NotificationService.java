package com.example.requestprocessor.service;

import com.example.requestprocessor.dto.NotificationRequestDto;
import com.example.requestprocessor.service.strategy.AbstractNotificationHandler;
import com.example.requestprocessor.service.strategy.NotificationStrategyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationStrategyResolver resolver;

    @Transactional
    public void handle(NotificationRequestDto request) {
        AbstractNotificationHandler handler = resolver.resolve(request.type());
        handler.handle(request);
    }
}
