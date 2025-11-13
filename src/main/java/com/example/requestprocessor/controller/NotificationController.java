package com.example.requestprocessor.controller;

import com.example.requestprocessor.dto.NotificationRequestDto;
import com.example.requestprocessor.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NotificationRequestDto request) {
        service.handle(request);
        return ResponseEntity.accepted().build();
    }
}
