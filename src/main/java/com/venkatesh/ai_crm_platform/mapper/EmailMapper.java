package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.email.EmailRequestDto;
import com.venkatesh.ai_crm_platform.dto.email.EmailResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Email;

public class EmailMapper {

    private EmailMapper(){}

    public static Email toEntity(EmailRequestDto dto){

        Email email = new Email();

        email.setSubject(dto.getSubject());
        email.setBody(dto.getBody());
        email.setStatus(dto.getStatus());

        return email;
    }

    public static EmailResponseDto toResponse(Email email){

        EmailResponseDto dto = new EmailResponseDto();

        dto.setId(email.getId());
        dto.setSubject(email.getSubject());
        dto.setBody(email.getBody());
        dto.setSentAt(email.getSentAt());
        dto.setStatus(email.getStatus());

        if(email.getCustomer()!=null){
            dto.setCustomerId(email.getCustomer().getId());
            dto.setCustomerName(email.getCustomer().getName());
        }

        if(email.getCampaign()!=null){
            dto.setCampaignId(email.getCampaign().getId());
            dto.setCampaignName(email.getCampaign().getName());
        }

        return dto;
    }
}