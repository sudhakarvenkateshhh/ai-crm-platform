package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.TicketService;
import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket){
        return ticketService.create(ticket);
    }

    @GetMapping
    public List<Ticket> getAll(){
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id){
        return ticketService.getById(id);
    }

    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id,
                         @RequestBody Ticket ticket){
        return ticketService.update(id, ticket);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        ticketService.delete(id);
        return "Ticket Deleted Successfully";
    }
}
