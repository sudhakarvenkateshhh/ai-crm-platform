package com.venkatesh.ai_crm_platform.dto.email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailRequestDto {

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    @NotBlank
    private String status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long campaignId;
}