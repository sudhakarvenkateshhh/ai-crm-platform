package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CampaignRepository;
import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignRequestDto;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.CampaignMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Campaign;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;

    public CampaignResponseDto create(CampaignRequestDto request){

        Campaign campaign = CampaignMapper.toEntity(request);

        User user = userRepository.findById(request.getCreatedById())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        campaign.setCreatedBy(user);

        return CampaignMapper.toResponse(
                campaignRepository.save(campaign));
    }

    public List<CampaignResponseDto> getAll(){

        return campaignRepository.findAll()
                .stream()
                .map(CampaignMapper::toResponse)
                .toList();
    }

    public CampaignResponseDto getById(Long id){

        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Campaign Not Found"));

        return CampaignMapper.toResponse(campaign);
    }

    public CampaignResponseDto update(Long id,
                                      CampaignRequestDto request){

        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Campaign Not Found"));

        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());

        User user = userRepository.findById(request.getCreatedById())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        campaign.setCreatedBy(user);

        return CampaignMapper.toResponse(
                campaignRepository.save(campaign));
    }

    public void delete(Long id){

        if(!campaignRepository.existsById(id)){
            throw new ResourceNotFoundException("Campaign Not Found");
        }

        campaignRepository.deleteById(id);
    }
}