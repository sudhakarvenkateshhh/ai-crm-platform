package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {
}