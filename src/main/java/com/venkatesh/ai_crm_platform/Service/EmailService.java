package com.venkatesh.ai_crm_platform.Service;


import com.venkatesh.ai_crm_platform.Repository.EmailRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    public Email create(Email email){
        return emailRepository.save(email);
    }

    public List<Email> getAll(){
        return emailRepository.findAll();
    }

    public Email getById(Long id){
        return emailRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Email Not Found"));
    }

    public void delete(Long id){
        emailRepository.deleteById(id);
    }
}
