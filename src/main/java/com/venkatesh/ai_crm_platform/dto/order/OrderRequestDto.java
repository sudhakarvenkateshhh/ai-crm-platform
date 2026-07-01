package com.venkatesh.ai_crm_platform.dto.order;

import com.venkatesh.ai_crm_platform.dto.orderitem.OrderItemRequestDto;
import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private Long customerId;

    private OrderStatus status;

    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemRequestDto> items;

}