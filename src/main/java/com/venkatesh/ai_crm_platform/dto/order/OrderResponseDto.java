package com.venkatesh.ai_crm_platform.dto.order;

import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {

    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private OrderStatus status;

    private Long customerId;

    private String customerName;

    private Long productId;

    private String productName;
}