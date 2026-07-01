package com.venkatesh.ai_crm_platform.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopCustomerDto {

    private Long customerId;
    private String customerName;
    private Double totalPurchase;

}