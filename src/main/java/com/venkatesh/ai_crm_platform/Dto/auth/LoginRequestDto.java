package com.venkatesh.ai_crm_platform.Dto.auth;





import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;
}