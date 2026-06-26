package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.TicketCommentService;
import com.venkatesh.ai_crm_platform.models.Entities.TicketComment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class TicketCommentController {

    private final TicketCommentService ticketCommentService;

    @PostMapping
    public TicketComment create(@RequestBody TicketComment comment){
        return ticketCommentService.create(comment);
    }

    @GetMapping
    public List<TicketComment> getAll(){
        return ticketCommentService.getAll();
    }

    @GetMapping("/{id}")
    public TicketComment getById(@PathVariable Long id){
        return ticketCommentService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        ticketCommentService.delete(id);
        return "Comment Deleted Successfully";
    }
}
