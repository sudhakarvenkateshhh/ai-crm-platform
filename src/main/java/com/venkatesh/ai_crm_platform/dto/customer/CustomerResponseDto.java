
package com.venkatesh.ai_crm_platform.dto.customer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponseDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String companyName;

    private String address;
    private String profileImage;

    private LocalDateTime createdAt;

    private Long assignedSalesPersonId;

    private String assignedSalesPersonName;
}