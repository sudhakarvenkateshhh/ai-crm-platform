package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Email;
import org.springframework.data.jpa.domain.Specification;

public class EmailSpecification {

    public static Specification<Email> search(String keyword){

        return (root, query, cb)->{

            if(keyword==null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern="%"+keyword.toLowerCase()+"%";

            return cb.or(

                    cb.like(cb.lower(root.get("subject")),pattern),

                    cb.like(cb.lower(root.get("body")),pattern),

                    cb.like(cb.lower(root.get("customer").get("name")),pattern),

                    cb.like(cb.lower(root.get("campaign").get("name")),pattern)

            );

        };

    }

    public static Specification<Email> status(String status){

        return (root,query,cb)->{

            if(status==null || status.isBlank()){
                return cb.conjunction();
            }

            return cb.equal(root.get("status"),status);

        };

    }

}