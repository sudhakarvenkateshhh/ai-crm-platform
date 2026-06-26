package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.order.OrderRequestDto;
import com.venkatesh.ai_crm_platform.dto.order.OrderResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static Order toEntity(OrderRequestDto dto){

        Order order = new Order();

        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());

        return order;
    }

    public static OrderResponseDto toResponse(Order order){

        OrderResponseDto dto = new OrderResponseDto();

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());

        if(order.getCustomer()!=null){
            dto.setCustomerId(order.getCustomer().getId());
            dto.setCustomerName(order.getCustomer().getName());
        }

        if(order.getProduct()!=null){
            dto.setProductId(order.getProduct().getId());
            dto.setProductName(order.getProduct().getName());
        }

        return dto;
    }
}