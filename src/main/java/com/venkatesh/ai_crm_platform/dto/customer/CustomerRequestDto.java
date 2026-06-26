
package com.venkatesh.ai_crm_platform.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    private String phone;

    private String companyName;

    private String address;

    // User ID instead of entire User entity
    private Long assignedSalesPersonId;
}