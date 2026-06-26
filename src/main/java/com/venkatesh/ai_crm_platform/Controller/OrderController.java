package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.OrderService;
import com.venkatesh.ai_crm_platform.dto.order.OrderRequestDto;
import com.venkatesh.ai_crm_platform.dto.order.OrderResponseDto;
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
    public OrderResponseDto create(
            @Valid @RequestBody OrderRequestDto request){

        return orderService.create(request);
    }

    @GetMapping
    public List<OrderResponseDto> getAll(){

        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(
            @PathVariable Long id){

        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        orderService.delete(id);

        return "Order Deleted Successfully";
    }
}