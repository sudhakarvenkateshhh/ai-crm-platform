package com.venkatesh.ai_crm_platform.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyOrdersDto {

    private Integer month;

    private Long totalOrders;

}