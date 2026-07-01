package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.ProductService;
import com.venkatesh.ai_crm_platform.dto.product.ProductRequestDto;
import com.venkatesh.ai_crm_platform.dto.product.ProductResponseDto;
import com.venkatesh.ai_crm_platform.response.ApiResponse;
import com.venkatesh.ai_crm_platform.response.PageResponse;
import com.venkatesh.ai_crm_platform.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponseDto> create(
            @Valid @RequestBody ProductRequestDto request){

        return ResponseBuilder.success(
                "Product created successfully",
                productService.create(request));
    }
    @GetMapping
    public ApiResponse<PageResponse<ProductResponseDto>> getAll(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction,

            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) Integer stock
    ) {

        return ResponseBuilder.success(
                "Products fetched successfully",
                productService.getAll(
                        page,
                        size,
                        sortBy,
                        direction,
                        keyword,
                        stock
                )
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponseDto> getById(
            @PathVariable Long id){

        return ResponseBuilder.success(
                "Product fetched successfully",
                productService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto request){

        return ResponseBuilder.success(
                "Product updated successfully",
                productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id){

        productService.delete(id);

        return ResponseBuilder.success(
                "Product deleted successfully",
                null);
    }
}