package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.TicketCommentService;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentRequestDto;
import com.venkatesh.ai_crm_platform.dto.ticketcomment.TicketCommentResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.venkatesh.ai_crm_platform.response.PageResponse;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class TicketCommentController {

    private final TicketCommentService ticketCommentService;

    @PostMapping
    public ApiResponse<TicketCommentResponseDto> create(
            @Valid @RequestBody TicketCommentRequestDto request){

        return ResponseBuilder.success(
                "Comment created successfully",
                ticketCommentService.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<TicketCommentResponseDto>> getAll(

            @RequestParam(defaultValue="0")
            int page,

            @RequestParam(defaultValue="10")
            int size,

            @RequestParam(defaultValue="id")
            String sortBy,

            @RequestParam(defaultValue="asc")
            String direction,

            @RequestParam(required=false)
            String keyword
    ){

        return ResponseBuilder.success(

                "Comments fetched successfully",

                ticketCommentService.getAll(

                        page,

                        size,

                        sortBy,

                        direction,

                        keyword

                )

        );

    }

    @GetMapping("/{id}")
    public ApiResponse<TicketCommentResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Comment fetched successfully",
                ticketCommentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        ticketCommentService.delete(id);

        return ResponseBuilder.success(
                "Comment deleted successfully",
                null);
    }
}