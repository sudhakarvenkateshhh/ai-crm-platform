package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.TicketComment;
import org.springframework.data.jpa.domain.Specification;

public class TicketCommentSpecification {

    public static Specification<TicketComment> search(String keyword){

        return (root, query, cb)->{

            if(keyword==null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern="%"+keyword.toLowerCase()+"%";

            return cb.or(

                    cb.like(cb.lower(root.get("message")),pattern),

                    cb.like(cb.lower(root.get("ticket").get("title")),pattern),

                    cb.like(cb.lower(root.get("user").get("name")),pattern)

            );

        };

    }

}