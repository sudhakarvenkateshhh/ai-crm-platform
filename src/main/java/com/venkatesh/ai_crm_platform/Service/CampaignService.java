package com.venkatesh.ai_crm_platform.Service;


import com.venkatesh.ai_crm_platform.Repository.CampaignRepository;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.models.Entities.Campaign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public Campaign create(Campaign campaign){
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAll(){
        return campaignRepository.findAll();
    }

    public Campaign getById(Long id){
        return campaignRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Campaign Not Found"));
    }

    public Campaign update(Long id,
                           Campaign campaign){

        Campaign existing = getById(id);

        existing.setName(campaign.getName());
        existing.setDescription(
                campaign.getDescription());

        existing.setStartDate(
                campaign.getStartDate());

        existing.setEndDate(
                campaign.getEndDate());

        return campaignRepository.save(existing);
    }

    public void delete(Long id){
        campaignRepository.deleteById(id);
    }
}