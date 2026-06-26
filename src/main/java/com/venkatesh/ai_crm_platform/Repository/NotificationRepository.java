package com.venkatesh.ai_crm_platform.Repository;


import com.venkatesh.ai_crm_platform.models.Entities.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notifications, Long> {
}