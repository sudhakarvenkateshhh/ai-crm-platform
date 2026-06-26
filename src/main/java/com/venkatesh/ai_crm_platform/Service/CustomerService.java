package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CustomerRepository;
import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerRequestDto;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.CustomerMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    // CREATE
    public CustomerResponseDto create(CustomerRequestDto request) {

        Customer customer = CustomerMapper.toEntity(request);

        customer.setCreatedAt(LocalDateTime.now());

        if (request.getAssignedSalesPersonId() != null) {

            User salesPerson = userRepository.findById(
                            request.getAssignedSalesPersonId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Sales Person Not Found"));

            customer.setAssignedSalesPerson(salesPerson);
        }

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toResponse(savedCustomer);
    }

    // GET ALL
    public List<CustomerResponseDto> getAll() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    // GET BY ID
    public CustomerResponseDto getById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer Not Found"));

        return CustomerMapper.toResponse(customer);
    }

    // UPDATE
    public CustomerResponseDto update(Long id,
                                      CustomerRequestDto request) {

        Customer existing = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer Not Found"));

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setCompanyName(request.getCompanyName());
        existing.setAddress(request.getAddress());

        if (request.getAssignedSalesPersonId() != null) {

            User salesPerson = userRepository.findById(
                            request.getAssignedSalesPersonId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Sales Person Not Found"));

            existing.setAssignedSalesPerson(salesPerson);
        }

        Customer updatedCustomer = customerRepository.save(existing);

        return CustomerMapper.toResponse(updatedCustomer);
    }

    // DELETE
    public void delete(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer Not Found");
        }

        customerRepository.deleteById(id);
    }
}