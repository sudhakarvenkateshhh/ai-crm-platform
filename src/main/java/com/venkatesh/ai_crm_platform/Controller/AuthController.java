package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Dto.auth.LoginRequestDto;
import com.venkatesh.ai_crm_platform.Dto.auth.LoginResponseDto;
import com.venkatesh.ai_crm_platform.Dto.auth.RegisterRequestDto;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import com.venkatesh.ai_crm_platform.security.JwtService;
import com.venkatesh.ai_crm_platform.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequestDto request) {

        return userService.register(request);
    }
    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(userDetails);

        return new LoginResponseDto(token);
    }
}