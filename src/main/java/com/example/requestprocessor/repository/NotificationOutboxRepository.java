package com.example.requestprocessor.repository;

import com.example.requestprocessor.model.NotificationOutbox;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NotificationOutboxRepository extends JpaRepository<NotificationOutbox, UUID> {
    @Query("""
           select n from NotificationOutbox n
           where n.sent = false
           order by n.createdAt asc
           """)
    List<NotificationOutbox> findBatch(Pageable pageable);
}
