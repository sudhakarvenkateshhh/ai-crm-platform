package com.venkatesh.ai_crm_platform.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopProductDto {

    private Long productId;

    private String productName;

    private Long quantitySold;

}