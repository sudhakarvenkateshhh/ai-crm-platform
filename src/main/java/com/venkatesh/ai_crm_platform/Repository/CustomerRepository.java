package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}