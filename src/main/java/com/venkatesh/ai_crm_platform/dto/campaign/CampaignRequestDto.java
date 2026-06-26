package com.venkatesh.ai_crm_platform.dto.campaign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CampaignRequestDto {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Long createdById;
}