package com.venkatesh.ai_crm_platform.models.Entities;

import com.venkatesh.ai_crm_platform.models.Enum.Priority;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime createdAt;
    private String attachment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;
}
