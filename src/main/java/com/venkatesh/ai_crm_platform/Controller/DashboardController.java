package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.DashboardService;
import com.venkatesh.ai_crm_platform.dto.dashboard.*;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.List;
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardResponseDto> getDashboard(){

        return ResponseBuilder.success(
                "Dashboard fetched successfully",
                dashboardService.getDashboard()
        );

    }
    @GetMapping("/products/top")
    public ApiResponse<List<TopProductDto>> getTopProducts(){

        return ResponseBuilder.success(

                "Top products fetched successfully",

                dashboardService.getTopProducts()

        );

    }
    @GetMapping("/orders/monthly")
    public ApiResponse<List<MonthlyOrdersDto>> getMonthlyOrders(){

        return ResponseBuilder.success(
                "Monthly orders fetched successfully",
                dashboardService.getMonthlyOrders()
        );

    }
    @GetMapping("/customers/top")
    public ApiResponse<List<TopCustomerDto>> getTopCustomers(){

        return ResponseBuilder.success(

                "Top customers fetched successfully",

                dashboardService.getTopCustomers()

        );

    }
    @GetMapping("/customers/monthly")
    public ApiResponse<List<CustomerGrowthDto>> getCustomerGrowth(){

        return ResponseBuilder.success(

                "Customer growth fetched successfully",

                dashboardService.getCustomerGrowth()

        );

    }
    @GetMapping("/tickets/status")
    public ApiResponse<List<TicketStatusDto>> getTicketStatus(){

        return ResponseBuilder.success(

                "Ticket status fetched successfully",

                dashboardService.getTicketStatus()

        );

    }
    @GetMapping("/revenue/monthly")
    public ApiResponse<List<MonthlyRevenueDto>> getMonthlyRevenue(){

        return ResponseBuilder.success(

                "Monthly revenue fetched successfully",

                dashboardService.getMonthlyRevenue()

        );

    }

}