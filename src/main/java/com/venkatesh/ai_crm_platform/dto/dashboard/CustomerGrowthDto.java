package com.venkatesh.ai_crm_platform.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerGrowthDto {

    private Integer month;
    private Long totalCustomers;

}