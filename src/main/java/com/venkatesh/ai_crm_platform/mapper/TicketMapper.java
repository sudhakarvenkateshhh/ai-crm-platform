package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.ticket.TicketRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Ticket;

public class TicketMapper {

    private TicketMapper(){}

    public static Ticket toEntity(TicketRequestDto dto){

        Ticket ticket = new Ticket();

        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus(dto.getStatus());
        ticket.setPriority(dto.getPriority());

        return ticket;
    }

    public static TicketResponseDto toResponse(Ticket ticket){

        TicketResponseDto dto = new TicketResponseDto();

        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setPriority(ticket.getPriority());
        dto.setCreatedAt(ticket.getCreatedAt());

        if(ticket.getCustomer()!=null){
            dto.setCustomerId(ticket.getCustomer().getId());
            dto.setCustomerName(ticket.getCustomer().getName());
        }

        if(ticket.getAssignedTo()!=null){
            dto.setAssignedToId(ticket.getAssignedTo().getId());
            dto.setAssignedToName(ticket.getAssignedTo().getName());
        }

        return dto;
    }
}