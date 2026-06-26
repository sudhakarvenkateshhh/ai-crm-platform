package com.venkatesh.ai_crm_platform.Dto;


import com.venkatesh.ai_crm_platform.models.Enum.Role;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private Role role;

    private Boolean active;
}