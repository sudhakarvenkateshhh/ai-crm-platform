package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.EmailService;
import com.venkatesh.ai_crm_platform.dto.email.EmailRequestDto;
import com.venkatesh.ai_crm_platform.dto.email.EmailResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.venkatesh.ai_crm_platform.response.PageResponse;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ApiResponse<EmailResponseDto> create(
            @Valid @RequestBody EmailRequestDto request){

        return ResponseBuilder.success(
                "Email created successfully",
                emailService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<EmailResponseDto>> getAll(

            @RequestParam(defaultValue="0")
            int page,

            @RequestParam(defaultValue="10")
            int size,

            @RequestParam(defaultValue="id")
            String sortBy,

            @RequestParam(defaultValue="asc")
            String direction,

            @RequestParam(required=false)
            String keyword,

            @RequestParam(required=false)
            String status
    ){

        return ResponseBuilder.success(

                "Emails fetched successfully",

                emailService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword,

                        status

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<EmailResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Email fetched successfully",
                emailService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        emailService.delete(id);

        return ResponseBuilder.success(
                "Email deleted successfully",
                null);
    }
}