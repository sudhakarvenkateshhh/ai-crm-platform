package com.venkatesh.ai_crm_platform.Service;

import com.venkatesh.ai_crm_platform.Repository.CustomerRepository;
import com.venkatesh.ai_crm_platform.Repository.TicketRepository;
import com.venkatesh.ai_crm_platform.Repository.UserRepository;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketResponseDto;
import com.venkatesh.ai_crm_platform.exception.ResourceNotFoundException;
import com.venkatesh.ai_crm_platform.mapper.TicketMapper;
import com.venkatesh.ai_crm_platform.models.Entities.Customer;
import com.venkatesh.ai_crm_platform.models.Entities.Ticket;
import com.venkatesh.ai_crm_platform.models.Entities.User;
import com.venkatesh.ai_crm_platform.models.Enum.Priority;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.specification.TicketSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public TicketResponseDto create(TicketRequestDto request){

        Ticket ticket = TicketMapper.toEntity(request);

        ticket.setCreatedAt(LocalDateTime.now());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));

        User user = userRepository.findById(request.getAssignedToId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        ticket.setCustomer(customer);
        ticket.setAssignedTo(user);

        return TicketMapper.toResponse(ticketRepository.save(ticket));
    }

    public PageResponse<TicketResponseDto> getAll(

            int page,

            int size,

            String sortBy,

            String direction,

            String keyword,

            TicketStatus status,

            Priority priority

    ){

        Sort sort = direction.equalsIgnoreCase("desc")

                ? Sort.by(sortBy).descending()

                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Specification<Ticket> specification =

                Specification.where(
                                TicketSpecification.search(keyword)
                        )
                        .and(
                                TicketSpecification.status(status)
                        )
                        .and(
                                TicketSpecification.priority(priority)
                        );

        Page<Ticket> ticketPage =
                ticketRepository.findAll(specification,pageable);

        List<TicketResponseDto> ticketDtos =

                ticketPage.getContent()

                        .stream()

                        .map(TicketMapper::toResponse)

                        .toList();

        return new PageResponse<>(

                ticketDtos,

                ticketPage.getNumber(),

                ticketPage.getSize(),

                ticketPage.getTotalElements(),

                ticketPage.getTotalPages(),

                ticketPage.isLast()

        );

    }

    public TicketResponseDto getById(Long id){

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket Not Found"));

        return TicketMapper.toResponse(ticket);
    }

    public TicketResponseDto update(Long id, TicketRequestDto request){

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket Not Found"));

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setPriority(request.getPriority());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));

        User user = userRepository.findById(request.getAssignedToId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        ticket.setCustomer(customer);
        ticket.setAssignedTo(user);

        return TicketMapper.toResponse(ticketRepository.save(ticket));
    }

    public void delete(Long id){

        if(!ticketRepository.existsById(id)){
            throw new ResourceNotFoundException("Ticket Not Found");
        }

        ticketRepository.deleteById(id);
    }
}