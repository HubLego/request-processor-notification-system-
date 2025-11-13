package com.example.requestprocessor.scheduler;

import com.example.requestprocessor.config.OutboxProperties;
import com.example.requestprocessor.model.NotificationOutbox;
import com.example.requestprocessor.repository.NotificationOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final NotificationOutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OutboxProperties props;

    @Scheduled(fixedDelayString = "${outbox.delay-ms}")
    public void flush() {
        List<NotificationOutbox> batch = outboxRepository.findBatch(PageRequest.of(0, props.getBatchSize()));
        if (batch.isEmpty()) return;

        for (NotificationOutbox m : batch) {
            try {
                kafkaTemplate.send(m.getTopic(), m.getKey(), m.getValue()).get();
                m.setSent(true);
                log.info("Отправлено в Kafka: key={}, topic={}", m.getKey(), m.getTopic());
            } catch (Exception e) {
                m.setAttempt(m.getAttempt() + 1);
                log.error("Ошибка отправки в Kafka: key={}, attempt={}, err={}", m.getKey(), m.getAttempt(), e.toString());
            }
        }
        outboxRepository.saveAll(batch);
    }
}
