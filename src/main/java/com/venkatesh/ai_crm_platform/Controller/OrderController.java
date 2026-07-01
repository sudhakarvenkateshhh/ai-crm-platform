package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.OrderService;
import com.venkatesh.ai_crm_platform.dto.order.OrderRequestDto;
import com.venkatesh.ai_crm_platform.dto.order.OrderResponseDto;
import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponseDto> create(
            @Valid @RequestBody OrderRequestDto request){

        return ResponseBuilder.success(
                "Order created successfully",
                orderService.create(request));
    }


    @GetMapping
    public ApiResponse<PageResponse<OrderResponseDto>> getAll(

            @RequestParam(defaultValue="0")
            int page,

            @RequestParam(defaultValue="10")
            int size,

            @RequestParam(defaultValue="id")
            String sortBy,

            @RequestParam(defaultValue="asc")
            String direction,

            @RequestParam(required=false)
            String keyword,

            @RequestParam(required=false)
            OrderStatus status
    ){

        return ResponseBuilder.success(

                "Orders fetched successfully",

                orderService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword,

                        status

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Order fetched successfully",
                orderService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        orderService.delete(id);

        return ResponseBuilder.success(
                "Order deleted successfully",
                null);
    }
}