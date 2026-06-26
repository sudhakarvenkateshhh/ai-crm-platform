package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.TicketCommentService;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class TicketCommentController {

    private final TicketCommentService ticketCommentService;

    @PostMapping
    public TicketCommentResponseDto create(
            @Valid @RequestBody TicketCommentRequestDto request){

        return ticketCommentService.create(request);
    }

    @GetMapping
    public List<TicketCommentResponseDto> getAll(){

        return ticketCommentService.getAll();
    }

    @GetMapping("/{id}")
    public TicketCommentResponseDto getById(
            @PathVariable Long id){

        return ticketCommentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        ticketCommentService.delete(id);

        return "Comment Deleted Successfully";
    }
}