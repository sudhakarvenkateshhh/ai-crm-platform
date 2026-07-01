package com.venkatesh.ai_crm_platform.dto.order;

import com.venkatesh.ai_crm_platform.dto.orderitem.OrderItemResponseDto;
import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {

    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private OrderStatus status;

    private Long customerId;

    private String customerName;

    private List<OrderItemResponseDto> items;

}