package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CampaignRepository;
import com.venkatesh.ai_crm_platform.Repository.CustomerRepository;
import com.venkatesh.ai_crm_platform.Repository.EmailRepository;
import com.venkatesh.ai_crm_platform.dto.email.EmailRequestDto;
import com.venkatesh.ai_crm_platform.dto.email.EmailResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.EmailMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Campaign;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import com.venkatesh.ai_crm_platform.models.Entities.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.specification.EmailSpecification;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final CustomerRepository customerRepository;
    private final CampaignRepository campaignRepository;

    public EmailResponseDto create(EmailRequestDto request){

        Email email = EmailMapper.toEntity(request);

        email.setSentAt(LocalDateTime.now());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));

        Campaign campaign = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign Not Found"));

        email.setCustomer(customer);
        email.setCampaign(campaign);

        return EmailMapper.toResponse(emailRepository.save(email));
    }

    public PageResponse<EmailResponseDto> getAll(

            int page,

            int size,

            String sortBy,

            String direction,

            String keyword,

            String status

    ){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Specification<Email> specification =

                Specification.where(
                        EmailSpecification.search(keyword)
                ).and(
                        EmailSpecification.status(status)
                );

        Page<Email> emailPage =
                emailRepository.findAll(specification,pageable);

        List<EmailResponseDto> emailDtos =

                emailPage.getContent()

                        .stream()

                        .map(EmailMapper::toResponse)

                        .toList();

        return new PageResponse<>(

                emailDtos,

                emailPage.getNumber(),

                emailPage.getSize(),

                emailPage.getTotalElements(),

                emailPage.getTotalPages(),

                emailPage.isLast()

        );

    }

    public EmailResponseDto getById(Long id){

        Email email = emailRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Email Not Found"));

        return EmailMapper.toResponse(email);
    }

    public void delete(Long id){

        if(!emailRepository.existsById(id)){
            throw new ResourceNotFoundException("Email Not Found");
        }

        emailRepository.deleteById(id);
    }
}