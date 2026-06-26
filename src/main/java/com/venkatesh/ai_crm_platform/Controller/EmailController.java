package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.EmailService;
import com.venkatesh.ai_crm_platform.dto.email.EmailRequestDto;
import com.venkatesh.ai_crm_platform.dto.email.EmailResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public EmailResponseDto create(
            @Valid @RequestBody EmailRequestDto request){

        return emailService.create(request);
    }

    @GetMapping
    public List<EmailResponseDto> getAll(){

        return emailService.getAll();
    }

    @GetMapping("/{id}")
    public EmailResponseDto getById(
            @PathVariable Long id){

        return emailService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        emailService.delete(id);

        return "Email Deleted Successfully";
    }
}