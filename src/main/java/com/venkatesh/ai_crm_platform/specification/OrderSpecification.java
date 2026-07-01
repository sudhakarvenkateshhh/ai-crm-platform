package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Order;
import com.venkatesh.ai_crm_platform.models.Enum.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    public static Specification<Order> search(String keyword) {

        return (root, query, cb) -> {

            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }

            query.distinct(true);

            String pattern = "%" + keyword.toLowerCase() + "%";

            var itemJoin = root.join("items");
            var productJoin = itemJoin.join("product");

            return cb.or(

                    cb.like(
                            cb.lower(root.get("customer").get("name")),
                            pattern
                    ),

                    cb.like(
                            cb.lower(productJoin.get("name")),
                            pattern
                    )

            );
        };
    }

    public static Specification<Order> status(OrderStatus status){

        return (root,query,cb)->{

            if(status==null){
                return cb.conjunction();
            }

            return cb.equal(root.get("status"),status);

        };

    }

}