package com.venkatesh.ai_crm_platform.Repository;

import com.venkatesh.ai_crm_platform.models.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends
        JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    @Query("""
SELECT COALESCE(SUM(o.totalAmount), 0)
FROM Order o
""")
    Double getTotalRevenue();

    @Query("""
SELECT
MONTH(o.orderDate),
COUNT(o)
FROM Order o
GROUP BY MONTH(o.orderDate)
ORDER BY MONTH(o.orderDate)
""")
    List<Object[]> getMonthlyOrders();

    @Query("""
SELECT
MONTH(o.orderDate),
SUM(o.totalAmount)
FROM Order o
GROUP BY MONTH(o.orderDate)
ORDER BY MONTH(o.orderDate)
""")
    List<Object[]> getMonthlyRevenue();

    @Query("""
SELECT
o.customer.id,
o.customer.name,
SUM(o.totalAmount)
FROM Order o
GROUP BY o.customer.id,o.customer.name
ORDER BY SUM(o.totalAmount) DESC
""")
    List<Object[]> getTopCustomers();

}