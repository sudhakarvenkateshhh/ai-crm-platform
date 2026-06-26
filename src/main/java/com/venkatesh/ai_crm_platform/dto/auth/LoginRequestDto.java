package com.venkatesh.ai_crm_platform.dto.auth;





import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;
}