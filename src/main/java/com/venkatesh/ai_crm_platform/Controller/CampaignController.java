package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.CampaignService;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignRequestDto;
import com.venkatesh.ai_crm_platform.dto.campaign.CampaignResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.venkatesh.ai_crm_platform.response.PageResponse;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ApiResponse<CampaignResponseDto> create(
            @Valid @RequestBody CampaignRequestDto request){

        return ResponseBuilder.success(
                "Campaign created successfully",
                campaignService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<CampaignResponseDto>> getAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction,

            @RequestParam(required = false)
            String keyword,

            @RequestParam(required = false)
            Long createdById

    ){

        return ResponseBuilder.success(

                "Campaigns fetched successfully",

                campaignService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword,

                        createdById

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<CampaignResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Campaign fetched successfully",
                campaignService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<CampaignResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CampaignRequestDto request){

        return ResponseBuilder.success(
                "Campaign updated successfully",
                campaignService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        campaignService.delete(id);

        return ResponseBuilder.success(
                "Campaign deleted successfully",
                null);
    }
}