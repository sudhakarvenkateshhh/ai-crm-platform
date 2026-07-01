package com.venkatesh.ai_crm_platform.specification;

import com.venkatesh.ai_crm_platform.models.Entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> search(String keyword){

        return (root, query, cb)->{

            if(keyword==null || keyword.isBlank()){
                return cb.conjunction();
            }

            String pattern="%"+keyword.toLowerCase()+"%";

            return cb.or(

                    cb.like(cb.lower(root.get("name")),pattern),

                    cb.like(cb.lower(root.get("description")),pattern)

            );

        };

    }

    public static Specification<Product> stock(Integer stock){

        return (root,query,cb)->{

            if(stock==null){
                return cb.conjunction();
            }

            return cb.equal(root.get("stock"),stock);

        };

    }

}