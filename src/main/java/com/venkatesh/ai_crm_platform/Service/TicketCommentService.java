package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.TicketCommentRepository;
import com.venkatesh.ai_crm_platform.Repository.TicketRepository;
import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.TicketCommentMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import com.venkatesh.ai_crm_platform.models.Entities.TicketComment;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCommentService {

    private final TicketCommentRepository ticketCommentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketCommentResponseDto create(TicketCommentRequestDto request){

        TicketComment comment =
                TicketCommentMapper.toEntity(request);

        comment.setCreatedAt(LocalDateTime.now());

        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket Not Found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        comment.setTicket(ticket);
        comment.setUser(user);

        return TicketCommentMapper.toResponse(
                ticketCommentRepository.save(comment));
    }

    public List<TicketCommentResponseDto> getAll(){

        return ticketCommentRepository.findAll()
                .stream()
                .map(TicketCommentMapper::toResponse)
                .toList();
    }

    public TicketCommentResponseDto getById(Long id){

        TicketComment comment = ticketCommentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment Not Found"));

        return TicketCommentMapper.toResponse(comment);
    }

    public void delete(Long id){

        if(!ticketCommentRepository.existsById(id)){
            throw new ResourceNotFoundException("Comment Not Found");
        }

        ticketCommentRepository.deleteById(id);
    }
}