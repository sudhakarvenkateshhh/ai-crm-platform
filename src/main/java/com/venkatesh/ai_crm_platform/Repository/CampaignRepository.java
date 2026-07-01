package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends
        JpaRepository<Campaign, Long>,
        JpaSpecificationExecutor<Campaign> {

    @Query("""
SELECT COUNT(c)
FROM Campaign c
WHERE CURRENT_TIMESTAMP BETWEEN c.startDate AND c.endDate
""")
    Long countActiveCampaigns();
}