package com.venkatesh.ai_crm_platform.Repository;


import com.venkatesh.ai_crm_platform.models.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long> {
}