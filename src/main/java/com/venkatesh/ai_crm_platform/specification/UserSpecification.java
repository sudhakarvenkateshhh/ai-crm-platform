package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.User;
import com.venkatesh.ai_crm_platform.models.Enum.Role;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> search(String keyword){

        return (root, query, cb)->{

            if(keyword==null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(

                    cb.like(cb.lower(root.get("name")), pattern),

                    cb.like(cb.lower(root.get("email")), pattern)

            );

        };

    }

    public static Specification<User> role(Role role){

        return (root, query, cb)->{

            if(role==null){
                return cb.conjunction();
            }

            return cb.equal(root.get("role"), role);

        };

    }

    public static Specification<User> active(Boolean active){

        return (root, query, cb)->{

            if(active==null){
                return cb.conjunction();
            }

            return cb.equal(root.get("active"), active);

        };

    }

}