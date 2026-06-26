package com.venkatesh.ai_crm_platform.dto.email;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailResponseDto {

    private Long id;

    private String subject;

    private String body;

    private LocalDateTime sentAt;

    private String status;

    private Long customerId;

    private String customerName;

    private Long campaignId;

    private String campaignName;
}