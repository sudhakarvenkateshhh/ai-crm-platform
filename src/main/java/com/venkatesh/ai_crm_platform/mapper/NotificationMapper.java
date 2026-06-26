package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.notification.NotificationRequestDto;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Notifications;

public class NotificationMapper {

    private NotificationMapper(){}

    public static Notifications toEntity(NotificationRequestDto dto){

        Notifications notification = new Notifications();

        notification.setMessage(dto.getMessage());
        notification.setIsRead(dto.getIsRead());

        return notification;
    }

    public static NotificationResponseDto toResponse(Notifications notification){

        NotificationResponseDto dto = new NotificationResponseDto();

        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setIsRead(notification.getIsRead());
        dto.setCreatedAt(notification.getCreatedAt());

        if(notification.getUser()!=null){

            dto.setUserId(notification.getUser().getId());
            dto.setUserName(notification.getUser().getName());
        }

        return dto;
    }
}