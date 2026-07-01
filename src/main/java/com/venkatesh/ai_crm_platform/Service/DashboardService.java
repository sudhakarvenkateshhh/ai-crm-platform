package com.venkatesh.ai_crm_platform.Service;
import com.venkatesh.ai_crm_platform.Repository.OrderItemRepository;
import com.venkatesh.ai_crm_platform.dto.dashboard.*;

import java.util.ArrayList;
import com.venkatesh.ai_crm_platform.Repository.*;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final TicketRepository ticketRepository;

    private final CampaignRepository campaignRepository;

    private final EmailRepository emailRepository;

    private final NotificationRepository notificationRepository;
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public DashboardResponseDto getDashboard(){

        DashboardResponseDto dto = new DashboardResponseDto();

        dto.setTotalUsers(userRepository.count());

        dto.setTotalCustomers(customerRepository.count());

        dto.setTotalProducts(productRepository.count());

        dto.setTotalOrders(orderRepository.count());

        dto.setTotalEmails(emailRepository.count());
        dto.setTotalRevenue(
                orderRepository.getTotalRevenue()
        );
        dto.setOpenTickets(
                ticketRepository.countByStatus(TicketStatus.OPEN)
        );

        dto.setClosedTickets(
                ticketRepository.countByStatus(TicketStatus.CLOSED)
        );
        dto.setActiveCampaigns(
                campaignRepository.countActiveCampaigns()
        );

        dto.setUnreadNotifications(
                notificationRepository.countByIsRead(false)
        );
        return dto;
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<MonthlyOrdersDto> getMonthlyOrders() {

        List<Object[]> result = orderRepository.getMonthlyOrders();

        List<MonthlyOrdersDto> response = new ArrayList<>();

        for (Object[] row : result) {

            Integer month = (Integer) row[0];

            Long totalOrders = (Long) row[1];

            response.add(new MonthlyOrdersDto(month, totalOrders));
        }

        return response;
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<MonthlyRevenueDto> getMonthlyRevenue(){

        List<Object[]> result =
                orderRepository.getMonthlyRevenue();

        List<MonthlyRevenueDto> response =
                new ArrayList<>();

        for(Object[] row : result){

            response.add(

                    new MonthlyRevenueDto(

                            ((Number)row[0]).intValue(),

                            ((Number)row[1]).doubleValue()

                    )

            );

        }

        return response;

    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<TopCustomerDto> getTopCustomers(){

        List<Object[]> result =
                orderRepository.getTopCustomers();

        List<TopCustomerDto> response =
                new ArrayList<>();

        for(Object[] row : result){

            response.add(

                    new TopCustomerDto(

                            ((Number)row[0]).longValue(),

                            (String)row[1],

                            ((Number)row[2]).doubleValue()

                    )

            );

        }

        return response;

    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<CustomerGrowthDto> getCustomerGrowth(){

        List<Object[]> result =
                customerRepository.getCustomerGrowth();

        List<CustomerGrowthDto> response =
                new ArrayList<>();

        for(Object[] row : result){

            response.add(

                    new CustomerGrowthDto(

                            ((Number)row[0]).intValue(),

                            ((Number)row[1]).longValue()

                    )

            );

        }

        return response;

    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<TicketStatusDto> getTicketStatus(){

        List<Object[]> result =
                ticketRepository.getTicketStatus();

        List<TicketStatusDto> response =
                new ArrayList<>();

        for(Object[] row : result){

            response.add(

                    new TicketStatusDto(

                            (TicketStatus) row[0],

                            ((Number)row[1]).longValue()

                    )

            );

        }

        return response;

    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<TopProductDto> getTopProducts(){

        List<Object[]> result =
                orderItemRepository.getTopProducts();

        List<TopProductDto> response =
                new ArrayList<>();

        for(Object[] row : result){

            response.add(

                    new TopProductDto(

                            ((Number)row[0]).longValue(),

                            (String)row[1],

                            ((Number)row[2]).longValue()

                    )

            );

        }

        return response;

    }

}