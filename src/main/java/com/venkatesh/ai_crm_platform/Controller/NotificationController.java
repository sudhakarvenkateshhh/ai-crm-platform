package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.NotificationService;
import com.venkatesh.ai_crm_platform.models.Entities.Notifications;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService
            notificationService;

    @PostMapping
    public Notifications create(
            @RequestBody Notifications notification){

        return notificationService
                .create(notification);
    }

    @GetMapping
    public List<Notifications> getAll(){

        return notificationService.getAll();
    }

    @GetMapping("/{id}")
    public Notifications getById(
            @PathVariable Long id){

        return notificationService
                .getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        notificationService.delete(id);

        return "Notification Deleted";
    }
}