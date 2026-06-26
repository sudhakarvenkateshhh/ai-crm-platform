package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.TicketService;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public TicketResponseDto create(@Valid @RequestBody TicketRequestDto request){

        return ticketService.create(request);
    }

    @GetMapping
    public List<TicketResponseDto> getAll(){

        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public TicketResponseDto getById(@PathVariable Long id){

        return ticketService.getById(id);
    }

    @PutMapping("/{id}")
    public TicketResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody TicketRequestDto request){

        return ticketService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

        ticketService.delete(id);

        return "Ticket Deleted Successfully";
    }
}