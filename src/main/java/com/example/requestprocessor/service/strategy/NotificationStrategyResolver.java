package com.example.requestprocessor.service.strategy;

import com.example.requestprocessor.dto.NotificationType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationStrategyResolver {

    private final Map<NotificationType, AbstractNotificationHandler> map = new EnumMap<>(NotificationType.class);

    public NotificationStrategyResolver(List<AbstractNotificationHandler> handlers) {
        for (var h : handlers) map.put(h.getType(), h);
    }

    public AbstractNotificationHandler resolve(NotificationType type) {
        var h = map.get(type);
        if (h == null) throw new IllegalArgumentException("No handler for type: " + type);
        return h;
    }
}
