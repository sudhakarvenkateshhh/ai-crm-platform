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
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.specification.CampaignSpecification;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

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

    public PageResponse<CampaignResponseDto> getAll(

            int page,

            int size,

            String sortBy,

            String direction,

            String keyword,

            Long createdById

    ){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Campaign> specification =

                Specification.where(
                        CampaignSpecification.search(keyword)
                ).and(
                        CampaignSpecification.createdBy(createdById)
                );

        Page<Campaign> campaignPage =
                campaignRepository.findAll(specification, pageable);

        List<CampaignResponseDto> campaignDtos =

                campaignPage.getContent()

                        .stream()

                        .map(CampaignMapper::toResponse)

                        .toList();

        return new PageResponse<>(

                campaignDtos,

                campaignPage.getNumber(),

                campaignPage.getSize(),

                campaignPage.getTotalElements(),

                campaignPage.getTotalPages(),

                campaignPage.isLast()

        );

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