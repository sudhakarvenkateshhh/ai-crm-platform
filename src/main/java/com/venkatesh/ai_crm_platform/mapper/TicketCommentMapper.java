package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.TicketComment;

public class TicketCommentMapper {

    private TicketCommentMapper(){}

    public static TicketComment toEntity(TicketCommentRequestDto dto){

        TicketComment comment = new TicketComment();

        comment.setMessage(dto.getMessage());

        return comment;
    }

    public static TicketCommentResponseDto toResponse(TicketComment comment){

        TicketCommentResponseDto dto =
                new TicketCommentResponseDto();

        dto.setId(comment.getId());
        dto.setMessage(comment.getMessage());
        dto.setCreatedAt(comment.getCreatedAt());

        if(comment.getTicket()!=null){
            dto.setTicketId(comment.getTicket().getId());
            dto.setTicketTitle(comment.getTicket().getTitle());
        }

        if(comment.getUser()!=null){
            dto.setUserId(comment.getUser().getId());
            dto.setUserName(comment.getUser().getName());
        }

        return dto;
    }
}