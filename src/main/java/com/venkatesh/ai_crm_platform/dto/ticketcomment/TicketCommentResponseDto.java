package com.venkatesh.ai_crm_platform.dto.ticketcomment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketCommentResponseDto {

    private Long id;

    private String message;

    private LocalDateTime createdAt;

    private Long ticketId;

    private String ticketTitle;

    private Long userId;

    private String userName;
}