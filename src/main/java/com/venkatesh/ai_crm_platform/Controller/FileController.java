package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.CustomerService;
import com.venkatesh.ai_crm_platform.Service.ProductService;
import com.venkatesh.ai_crm_platform.Service.TicketService;
import com.venkatesh.ai_crm_platform.dto.customer.CustomerResponseDto;
import com.venkatesh.ai_crm_platform.dto.product.ProductResponseDto;
import com.venkatesh.ai_crm_platform.dto.ticket.TicketResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final TicketService ticketService;

    @PostMapping("/products/{id}")
    public ApiResponse<ProductResponseDto> uploadProductImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file){

        return ResponseBuilder.success(
                "Product image uploaded",
                productService.uploadImage(id,file)
        );
    }

    @PostMapping("/customers/{id}")
    public ApiResponse<CustomerResponseDto> uploadCustomerImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file){

        return ResponseBuilder.success(
                "Customer profile uploaded",
                customerService.uploadProfile(id,file)
        );
    }

    @PostMapping("/tickets/{id}")
    public ApiResponse<TicketResponseDto> uploadTicketFile(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file){

        return ResponseBuilder.success(
                "Ticket attachment uploaded",
                ticketService.uploadAttachment(id,file)
        );
    }
}