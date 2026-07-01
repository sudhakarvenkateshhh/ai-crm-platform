package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import com.venkatesh.ai_crm_platform.models.Enum.Priority;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecification {

    public static Specification<Ticket> search(String keyword){

        return (root, query, cb)->{

            if(keyword==null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern="%"+keyword.toLowerCase()+"%";

            return cb.or(

                    cb.like(cb.lower(root.get("title")),pattern),

                    cb.like(cb.lower(root.get("description")),pattern),

                    cb.like(cb.lower(root.get("customer").get("name")),pattern),

                    cb.like(cb.lower(root.get("assignedTo").get("name")),pattern)

            );

        };

    }

    public static Specification<Ticket> status(TicketStatus status){

        return (root,query,cb)->{

            if(status==null){
                return cb.conjunction();
            }

            return cb.equal(root.get("status"),status);

        };

    }

    public static Specification<Ticket> priority(Priority priority){

        return (root,query,cb)->{

            if(priority==null){
                return cb.conjunction();
            }

            return cb.equal(root.get("priority"),priority);

        };

    }

}