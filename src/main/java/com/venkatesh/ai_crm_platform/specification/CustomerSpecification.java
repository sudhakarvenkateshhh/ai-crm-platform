package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<Customer> search(String keyword){

        return (root, query, cb) -> {

            if(keyword == null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(

                    cb.like(cb.lower(root.get("name")), pattern),

                    cb.like(cb.lower(root.get("email")), pattern),

                    cb.like(cb.lower(root.get("companyName")), pattern)

            );

        };
    }

    public static Specification<Customer> company(String company){

        return (root, query, cb)->{

            if(company==null || company.isBlank()){

                return cb.conjunction();

            }

            return cb.equal(root.get("companyName"),company);

        };
    }

}