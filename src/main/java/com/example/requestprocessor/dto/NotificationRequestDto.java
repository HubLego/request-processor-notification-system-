package com.example.requestprocessor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationRequestDto(
    @NotNull
    NotificationType type,

    @NotBlank
    String message
) {}
