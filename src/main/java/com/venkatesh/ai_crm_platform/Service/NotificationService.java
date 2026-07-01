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
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.specification.NotificationSpecification;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

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

    public PageResponse<NotificationResponseDto> getAll(

            int page,

            int size,

            String sortBy,

            String direction,

            String keyword,

            Boolean isRead

    ){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Notifications> specification =

                Specification.where(
                        NotificationSpecification.search(keyword)
                ).and(
                        NotificationSpecification.isRead(isRead)
                );

        Page<Notifications> notificationPage =
                notificationRepository.findAll(specification, pageable);

        List<NotificationResponseDto> notificationDtos =

                notificationPage.getContent()

                        .stream()

                        .map(NotificationMapper::toResponse)

                        .toList();

        return new PageResponse<>(

                notificationDtos,

                notificationPage.getNumber(),

                notificationPage.getSize(),

                notificationPage.getTotalElements(),

                notificationPage.getTotalPages(),

                notificationPage.isLast()

        );

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