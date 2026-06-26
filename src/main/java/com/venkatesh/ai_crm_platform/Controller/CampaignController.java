package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.CampaignService;
import com.venkatesh.ai_crm_platform.models.Entities.Campaign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public Campaign create(
            @RequestBody Campaign campaign){

        return campaignService.create(campaign);
    }

    @GetMapping
    public List<Campaign> getAll(){
        return campaignService.getAll();
    }

    @GetMapping("/{id}")
    public Campaign getById(
            @PathVariable Long id){

        return campaignService.getById(id);
    }

    @PutMapping("/{id}")
    public Campaign update(
            @PathVariable Long id,
            @RequestBody Campaign campaign){

        return campaignService.update(id,
                campaign);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        campaignService.delete(id);

        return "Campaign Deleted";
    }
}