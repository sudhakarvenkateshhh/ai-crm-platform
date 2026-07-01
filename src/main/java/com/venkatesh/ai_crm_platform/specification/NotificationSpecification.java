package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Notifications;
import org.springframework.data.jpa.domain.Specification;

public class NotificationSpecification {

    public static Specification<Notifications> search(String keyword){

        return (root, query, cb) -> {

            if(keyword == null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(

                    cb.like(cb.lower(root.get("message")), pattern),

                    cb.like(cb.lower(root.get("user").get("name")), pattern)

            );

        };

    }

    public static Specification<Notifications> isRead(Boolean isRead){

        return (root, query, cb) -> {

            if(isRead == null){
                return cb.conjunction();
            }

            return cb.equal(root.get("isRead"), isRead);

        };

    }

}