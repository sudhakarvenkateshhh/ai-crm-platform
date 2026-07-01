package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.NotificationService;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationRequestDto;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.venkatesh.ai_crm_platform.response.PageResponse;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ApiResponse<NotificationResponseDto> create(
            @Valid @RequestBody NotificationRequestDto request){

        return ResponseBuilder.success(
                "Notification created successfully",
                notificationService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<NotificationResponseDto>> getAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction,

            @RequestParam(required = false)
            String keyword,

            @RequestParam(required = false)
            Boolean isRead

    ){

        return ResponseBuilder.success(

                "Notifications fetched successfully",

                notificationService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword,

                        isRead

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<NotificationResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Notification fetched successfully",
                notificationService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        notificationService.delete(id);

        return ResponseBuilder.success(
                "Notification deleted successfully",
                null);
    }
}