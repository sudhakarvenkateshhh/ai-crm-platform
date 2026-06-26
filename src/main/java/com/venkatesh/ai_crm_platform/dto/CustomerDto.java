package com.venkatesh.ai_crm_platform.dto;



import lombok.Data;

@Data
public class CustomerDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String companyName;

    private String address;
}