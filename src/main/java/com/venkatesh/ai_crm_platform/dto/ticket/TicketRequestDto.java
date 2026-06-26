package com.venkatesh.ai_crm_platform.dto.ticket;

import com.venkatesh.ai_crm_platform.models.Enum.Priority;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketRequestDto {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private TicketStatus status;

    @NotNull
    private Priority priority;

    @NotNull
    private Long customerId;

    @NotNull
    private Long assignedToId;
}