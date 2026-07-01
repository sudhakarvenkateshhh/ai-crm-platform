package com.venkatesh.ai_crm_platform.dto.ticket;

import com.venkatesh.ai_crm_platform.models.Enum.Priority;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketResponseDto {

    private Long id;

    private String title;

    private String description;

    private TicketStatus status;

    private Priority priority;

    private LocalDateTime createdAt;
    private String attachment;

    private Long customerId;

    private String customerName;

    private Long assignedToId;

    private String assignedToName;
}