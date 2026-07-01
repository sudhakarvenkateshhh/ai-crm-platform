package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.orderitem.OrderItemResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.OrderItem;

public class OrderItemMapper {

    private OrderItemMapper(){}

    public static OrderItemResponseDto toResponse(OrderItem item){

        return new OrderItemResponseDto(

                item.getProduct().getId(),

                item.getProduct().getName(),

                item.getQuantity(),

                item.getUnitPrice(),

                item.getQuantity()*item.getUnitPrice()

        );

    }

}