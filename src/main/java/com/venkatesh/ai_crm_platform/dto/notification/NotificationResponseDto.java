package com.venkatesh.ai_crm_platform.dto.notification;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponseDto {

    private Long id;

    private String message;

    private Boolean isRead;

    private LocalDateTime createdAt;

    private Long userId;

    private String userName;
}