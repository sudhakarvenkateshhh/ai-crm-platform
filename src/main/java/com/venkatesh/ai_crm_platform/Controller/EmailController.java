package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.EmailService;
import com.venkatesh.ai_crm_platform.models.Entities.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public Email create(
            @RequestBody Email email){

        return emailService.create(email);
    }

    @GetMapping
    public List<Email> getAll(){
        return emailService.getAll();
    }

    @GetMapping("/{id}")
    public Email getById(
            @PathVariable Long id){

        return emailService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        emailService.delete(id);

        return "Email Deleted";
    }
}
