package com.venkatesh.ai_crm_platform.Service;


import com.venkatesh.ai_crm_platform.Repository.OrderRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order Not Found"));
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }
}