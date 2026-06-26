package com.venkatesh.ai_crm_platform.Service;


import com.venkatesh.ai_crm_platform.Repository.NotificationRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Notifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository
            notificationRepository;

    public Notifications create(
            Notifications notification){

        return notificationRepository
                .save(notification);
    }

    public List<Notifications> getAll(){
        return notificationRepository.findAll();
    }

    public Notifications getById(Long id){
        return notificationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notification Not Found"));
    }

    public void delete(Long id){
        notificationRepository.deleteById(id);
    }
}