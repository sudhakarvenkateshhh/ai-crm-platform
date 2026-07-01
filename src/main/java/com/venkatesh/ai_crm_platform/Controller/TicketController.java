package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.TicketService;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketResponseDto;
import com.venkatesh.ai_crm_platform.models.Enum.Priority;
import com.venkatesh.ai_crm_platform.models.Enum.TicketStatus;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ApiResponse<TicketResponseDto> create(
            @Valid @RequestBody TicketRequestDto request){

        return ResponseBuilder.success(
                "Ticket created successfully",
                ticketService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<TicketResponseDto>> getAll(

            @RequestParam(defaultValue="0")
            int page,

            @RequestParam(defaultValue="10")
            int size,

            @RequestParam(defaultValue="id")
            String sortBy,

            @RequestParam(defaultValue="asc")
            String direction,

            @RequestParam(required=false)
            String keyword,

            @RequestParam(required=false)
            TicketStatus status,

            @RequestParam(required=false)
            Priority priority
    ){

        return ResponseBuilder.success(

                "Tickets fetched successfully",

                ticketService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword,

                        status,

                        priority

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<TicketResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Ticket fetched successfully",
                ticketService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<TicketResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody TicketRequestDto request){

        return ResponseBuilder.success(
                "Ticket updated successfully",
                ticketService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        ticketService.delete(id);

        return ResponseBuilder.success(
                "Ticket deleted successfully",
                null);
    }
}