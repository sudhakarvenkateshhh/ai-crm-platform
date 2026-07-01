package com.venkatesh.ai_crm_platform.dto.orderitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDto {

    @NotNull
    private Long productId;

    @Min(value = 1,message = "Quantity must be greater than 0")
    private Integer quantity;

}