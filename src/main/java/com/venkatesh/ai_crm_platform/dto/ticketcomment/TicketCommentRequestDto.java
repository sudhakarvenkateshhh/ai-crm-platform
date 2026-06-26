package com.venkatesh.ai_crm_platform.dto.ticketcomment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketCommentRequestDto {

    @NotBlank
    private String message;

    @NotNull
    private Long ticketId;

    @NotNull
    private Long userId;
}