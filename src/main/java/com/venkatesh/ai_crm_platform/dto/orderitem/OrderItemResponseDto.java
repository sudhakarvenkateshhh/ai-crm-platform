package com.venkatesh.ai_crm_platform.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {

    private Long productId;

    private String productName;

    private Integer quantity;

    private Double unitPrice;

    private Double subtotal;

}