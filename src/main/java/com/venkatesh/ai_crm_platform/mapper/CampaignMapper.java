package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.campaign.CampaignRequestDto;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Campaign;

public class CampaignMapper {

    private CampaignMapper(){}

    public static Campaign toEntity(CampaignRequestDto dto){

        Campaign campaign = new Campaign();

        campaign.setName(dto.getName());
        campaign.setDescription(dto.getDescription());
        campaign.setStartDate(dto.getStartDate());
        campaign.setEndDate(dto.getEndDate());

        return campaign;
    }

    public static CampaignResponseDto toResponse(Campaign campaign){

        CampaignResponseDto dto = new CampaignResponseDto();

        dto.setId(campaign.getId());
        dto.setName(campaign.getName());
        dto.setDescription(campaign.getDescription());
        dto.setStartDate(campaign.getStartDate());
        dto.setEndDate(campaign.getEndDate());

        if(campaign.getCreatedBy()!=null){
            dto.setCreatedById(campaign.getCreatedBy().getId());
            dto.setCreatedByName(campaign.getCreatedBy().getName());
        }

        return dto;
    }
}