package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CustomerRepository;
import com.venkatesh.ai_crm_platform.Repository.OrderRepository;
import com.venkatesh.ai_crm_platform.Repository.ProductRepository;
import com.venkatesh.ai_crm_platform.dto.order.OrderRequestDto;
import com.venkatesh.ai_crm_platform.dto.order.OrderResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.OrderMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import com.venkatesh.ai_crm_platform.models.Entities.Order;
import com.venkatesh.ai_crm_platform.models.Entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderResponseDto create(OrderRequestDto request){

        Order order = OrderMapper.toEntity(request);

        order.setOrderDate(LocalDateTime.now());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer Not Found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Not Found"));

        order.setCustomer(customer);
        order.setProduct(product);

        return OrderMapper.toResponse(
                orderRepository.save(order)
        );
    }

    public List<OrderResponseDto> getAll(){

        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    public OrderResponseDto getById(Long id){

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Found"));

        return OrderMapper.toResponse(order);
    }

    public void delete(Long id){

        if(!orderRepository.existsById(id)){
            throw new ResourceNotFoundException("Order Not Found");
        }

        orderRepository.deleteById(id);
    }
}