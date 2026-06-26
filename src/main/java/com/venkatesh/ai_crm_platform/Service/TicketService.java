package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.TicketRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket create(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    public Ticket getById(Long id){
        return ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ticket Not Found"));
    }

    public Ticket update(Long id, Ticket ticket){

        Ticket existing = getById(id);

        existing.setTitle(ticket.getTitle());
        existing.setDescription(ticket.getDescription());
        existing.setPriority(ticket.getPriority());
        existing.setStatus(ticket.getStatus());

        return ticketRepository.save(existing);
    }

    public void delete(Long id){
        ticketRepository.deleteById(id);
    }
}