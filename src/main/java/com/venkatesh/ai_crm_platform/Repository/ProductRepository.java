package com.venkatesh.ai_crm_platform.Repository;


import com.venkatesh.ai_crm_platform.models.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {
}