package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.CustomerService;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerRequestDto;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // CREATE
    @PostMapping
    public CustomerResponseDto create(
            @Valid @RequestBody CustomerRequestDto request) {

        return customerService.create(request);
    }

    // GET ALL
    @GetMapping
    public List<CustomerResponseDto> getAll() {

        return customerService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerResponseDto getById(
            @PathVariable Long id) {

        return customerService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto request) {

        return customerService.update(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        customerService.delete(id);

        return "Customer Deleted Successfully";
    }
}