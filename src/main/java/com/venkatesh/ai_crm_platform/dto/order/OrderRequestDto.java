package com.venkatesh.ai_crm_platform.dto.order;

import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {

    @NotNull
    private Double totalAmount;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long productId;
}