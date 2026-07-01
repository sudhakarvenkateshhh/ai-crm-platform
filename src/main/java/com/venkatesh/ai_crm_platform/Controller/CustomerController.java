package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.CustomerService;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerRequestDto;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerResponseDto> create(
            @Valid @RequestBody CustomerRequestDto request){

        return ResponseBuilder.success(
                "Customer created successfully",
                customerService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<CustomerResponseDto>> getAll(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction,

            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) String company
    ) {

        return ResponseBuilder.success(

                "Customers fetched successfully",

                customerService.getAll(
                        page,
                        size,
                        sortBy,
                        direction,
                        keyword,
                        company
                )
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<CustomerResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Customer fetched successfully",
                customerService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<CustomerResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto request){

        return ResponseBuilder.success(
                "Customer updated successfully",
                customerService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        customerService.delete(id);

        return ResponseBuilder.success(
                "Customer deleted successfully",
                null);
    }
}