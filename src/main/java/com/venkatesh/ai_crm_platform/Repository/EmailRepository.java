package com.venkatesh.ai_crm_platform.Repository;


import com.venkatesh.ai_crm_platform.models.Entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository
        extends JpaRepository<Email, Long> {
}