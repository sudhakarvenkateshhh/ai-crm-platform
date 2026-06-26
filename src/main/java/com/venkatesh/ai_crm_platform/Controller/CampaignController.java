package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.CampaignService;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignRequestDto;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public CampaignResponseDto create(
            @Valid @RequestBody CampaignRequestDto request){

        return campaignService.create(request);
    }

    @GetMapping
    public List<CampaignResponseDto> getAll(){

        return campaignService.getAll();
    }

    @GetMapping("/{id}")
    public CampaignResponseDto getById(
            @PathVariable Long id){

        return campaignService.getById(id);
    }

    @PutMapping("/{id}")
    public CampaignResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody CampaignRequestDto request){

        return campaignService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        campaignService.delete(id);

        return "Campaign Deleted Successfully";
    }
}