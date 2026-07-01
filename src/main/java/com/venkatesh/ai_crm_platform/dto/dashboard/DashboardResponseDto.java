package com.venkatesh.ai_crm_platform.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardResponseDto {

    private Long totalUsers;

    private Long totalCustomers;

    private Long totalProducts;

    private Long totalOrders;

    private Double totalRevenue;

    private Long openTickets;

    private Long closedTickets;

    private Long activeCampaigns;

    private Long totalEmails;

    private Long unreadNotifications;
}