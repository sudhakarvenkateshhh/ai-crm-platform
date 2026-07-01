package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository
        extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {


    @Query("""
SELECT
MONTH(c.createdAt),
COUNT(c)
FROM Customer c
GROUP BY MONTH(c.createdAt)
ORDER BY MONTH(c.createdAt)
""")
    List<Object[]> getCustomerGrowth();
}