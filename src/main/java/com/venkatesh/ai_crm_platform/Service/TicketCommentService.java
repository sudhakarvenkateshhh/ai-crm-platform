package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.TicketCommentRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.TicketComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCommentService {

    private final TicketCommentRepository ticketCommentRepository;

    public TicketComment create(TicketComment comment){
        return ticketCommentRepository.save(comment);
    }

    public List<TicketComment> getAll(){
        return ticketCommentRepository.findAll();
    }

    public TicketComment getById(Long id){
        return ticketCommentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "TicketComment Not Found"));
    }

    public void delete(Long id){
        ticketCommentRepository.deleteById(id);
    }
}