package com.venkatesh.ai_crm_platform.dto.campaign;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CampaignResponseDto {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long createdById;

    private String createdByName;
}