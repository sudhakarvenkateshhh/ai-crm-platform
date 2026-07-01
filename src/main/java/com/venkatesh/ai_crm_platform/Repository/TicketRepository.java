package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends
        JpaRepository<Ticket, Long>,
        JpaSpecificationExecutor<Ticket> {

    Long countByStatus(TicketStatus status);
    @Query("""
SELECT
t.status,
COUNT(t)
FROM Ticket t
GROUP BY t.status
""")
    List<Object[]> getTicketStatus();
}