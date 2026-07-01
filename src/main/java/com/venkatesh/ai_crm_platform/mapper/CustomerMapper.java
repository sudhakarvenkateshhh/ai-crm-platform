
package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.customer.CustomerRequestDto;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    // DTO -> Entity
    public static Customer toEntity(CustomerRequestDto dto) {

        Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setCompanyName(dto.getCompanyName());
        customer.setAddress(dto.getAddress());

        return customer;
    }

    // Entity -> DTO
    public static CustomerResponseDto toResponse(Customer customer) {

        CustomerResponseDto dto = new CustomerResponseDto();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCompanyName(customer.getCompanyName());
        dto.setAddress(customer.getAddress());
        dto.setProfileImage(customer.getProfileImage());
        dto.setCreatedAt(customer.getCreatedAt());

        if (customer.getAssignedSalesPerson() != null) {

            dto.setAssignedSalesPersonId(
                    customer.getAssignedSalesPerson().getId());

            dto.setAssignedSalesPersonName(
                    customer.getAssignedSalesPerson().getName());
        }

        return dto;
    }
}