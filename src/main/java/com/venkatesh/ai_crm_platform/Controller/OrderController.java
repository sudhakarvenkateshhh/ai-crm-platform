package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.OrderService;
import com.venkatesh.ai_crm_platform.models.Entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(
            @RequestBody Order order){

        return orderService.create(order);
    }

    @GetMapping
    public List<Order> getAll(){

        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(
            @PathVariable Long id){

        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        orderService.delete(id);

        return "Order Deleted";
    }
}