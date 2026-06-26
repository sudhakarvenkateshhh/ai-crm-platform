package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}