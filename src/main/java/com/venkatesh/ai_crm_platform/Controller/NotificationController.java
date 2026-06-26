package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.NotificationService;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationRequestDto;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponseDto create(
            @Valid @RequestBody NotificationRequestDto request){

        return notificationService.create(request);
    }

    @GetMapping
    public List<NotificationResponseDto> getAll(){

        return notificationService.getAll();
    }

    @GetMapping("/{id}")
    public NotificationResponseDto getById(
            @PathVariable Long id){

        return notificationService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        notificationService.delete(id);

        return "Notification Deleted Successfully";
    }
}