package com.venkatesh.ai_crm_platform.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequestDto {

    @NotBlank
    private String message;

    @NotNull
    private Boolean isRead;

    @NotNull
    private Long userId;
}