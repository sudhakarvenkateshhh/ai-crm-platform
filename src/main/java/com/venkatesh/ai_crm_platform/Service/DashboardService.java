package com.venkatesh.ai_crm_platform.Service;
import com.venkatesh.ai_crm_platform.Repository.OrderItemRepository;
import com.venkatesh.ai_crm_platform.dto.dashboard.TopProductDto;
import java.util.ArrayList;
import com.venkatesh.ai_crm_platform.Repository.*;
import com.venkatesh.ai_crm_platform.dto.dashboard.DashboardResponseDto;
import com.venkatesh.ai_crm_platform.dto.dashboard.MonthlyOrdersDto;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import lombok.RequiredArgsConstructor;
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