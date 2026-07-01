package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Campaign;
import org.springframework.data.jpa.domain.Specification;

public class CampaignSpecification {

    public static Specification<Campaign> search(String keyword){

        return (root, query, cb) -> {

            if(keyword == null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(

                    cb.like(cb.lower(root.get("name")), pattern),

                    cb.like(cb.lower(root.get("description")), pattern),

                    cb.like(cb.lower(root.get("createdBy").get("name")), pattern)

            );

        };

    }

    public static Specification<Campaign> createdBy(Long userId){

        return (root, query, cb) -> {

            if(userId == null){
                return cb.conjunction();
            }

            return cb.equal(root.get("createdBy").get("id"), userId);

        };

    }

}