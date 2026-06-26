package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.NotificationRepository;
import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationRequestDto;
import com.venkatesh.ai_crm_platform.dto.notification.NotificationResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.NotificationMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Notifications;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationResponseDto create(NotificationRequestDto request){

        Notifications notification =
                NotificationMapper.toEntity(request);

        notification.setCreatedAt(LocalDateTime.now());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        notification.setUser(user);

        return NotificationMapper.toResponse(
                notificationRepository.save(notification));
    }

    public List<NotificationResponseDto> getAll(){

        return notificationRepository.findAll()
                .stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    public NotificationResponseDto getById(Long id){

        Notifications notification =
                notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification Not Found"));

        return NotificationMapper.toResponse(notification);
    }

    public void delete(Long id){

        if(!notificationRepository.existsById(id)){
            throw new ResourceNotFoundException("Notification Not Found");
        }

        notificationRepository.deleteById(id);
    }
}