package com.venkatesh.ai_crm_platform.dto.dashboard;

import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketStatusDto {

    private TicketStatus status;
    private Long count;

}