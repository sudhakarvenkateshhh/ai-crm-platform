package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CustomerRepository;
import com.venkatesh.ai_crm_platform.Repository.OrderRepository;
import com.venkatesh.ai_crm_platform.Repository.ProductRepository;
import com.venkatesh.ai_crm_platform.dto.order.OrderRequestDto;
import com.venkatesh.ai_crm_platform.dto.order.OrderResponseDto;
import com.venkatesh.ai_crm_platform.dto.orderitem.OrderItemRequestDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.OrderMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import com.venkatesh.ai_crm_platform.models.Entities.Order;
import com.venkatesh.ai_crm_platform.models.Entities.OrderItem;
import com.venkatesh.ai_crm_platform.models.Entities.Product;
import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    // ================= CREATE =================
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN','SALES')")
    public OrderResponseDto create(OrderRequestDto request){

        Customer customer = customerRepository.findById(request.getCustomerId())

                .orElseThrow(()->
                        new ResourceNotFoundException("Customer Not Found"));

        Order order = new Order();

        order.setCustomer(customer);

        order.setOrderDate(LocalDateTime.now());

        order.setStatus(request.getStatus());

        double totalAmount = 0;

        for(OrderItemRequestDto itemDto : request.getItems()){

            Product product = productRepository.findById(itemDto.getProductId())

                    .orElseThrow(()->
                            new ResourceNotFoundException("Product Not Found"));

            OrderItem item = new OrderItem();

            item.setOrder(order);

            item.setProduct(product);

            item.setQuantity(itemDto.getQuantity());

            item.setUnitPrice(product.getPrice());

            if(product.getStock() < itemDto.getQuantity()){

                throw new RuntimeException(

                        product.getName() + " is out of stock"

                );

            }

            product.setStock(

                    product.getStock()

                            - itemDto.getQuantity()

            );



            totalAmount +=

                    product.getPrice()

                            * itemDto.getQuantity();

            order.getItems().add(item);

        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);

    }

    // ================= GET ALL =================

    @PreAuthorize("hasAnyRole('ADMIN','SALES','MANAGER')")
    public PageResponse<OrderResponseDto> getAll(

            int page,

            int size,

            String sortBy,

            String direction,

            String keyword,

            OrderStatus status

    ){

        Sort sort = direction.equalsIgnoreCase("desc")

                ? Sort.by(sortBy).descending()

                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Specification<Order> specification =

                Specification.where(
                        OrderSpecification.search(keyword)
                ).and(
                        OrderSpecification.status(status)
                );

        Page<Order> orderPage =

                orderRepository.findAll(specification,pageable);

        List<OrderResponseDto> dtos =

                orderPage.getContent()

                        .stream()

                        .map(OrderMapper::toResponse)

                        .toList();

        return new PageResponse<>(

                dtos,

                orderPage.getNumber(),

                orderPage.getSize(),

                orderPage.getTotalElements(),

                orderPage.getTotalPages(),

                orderPage.isLast()

        );

    }

    // ================= GET BY ID =================
    @PreAuthorize("hasAnyRole('ADMIN','SALES','MANAGER')")
    public OrderResponseDto getById(Long id){

        Order order = orderRepository.findById(id)

                .orElseThrow(()->
                        new ResourceNotFoundException("Order Not Found"));

        return OrderMapper.toResponse(order);

    }

    // ================= DELETE =================
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id){

        if(!orderRepository.existsById(id)){

            throw new ResourceNotFoundException("Order Not Found");

        }

        orderRepository.deleteById(id);

    }

}