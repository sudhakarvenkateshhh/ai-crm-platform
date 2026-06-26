package com.venkatesh.ai_crm_platform.Controller;

import com.venkatesh.ai_crm_platform.Service.ProductService;
import com.venkatesh.ai_crm_platform.dto.product.ProductRequestDto;
import com.venkatesh.ai_crm_platform.dto.product.ProductResponseDto;
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
    public ProductResponseDto create(
            @Valid @RequestBody ProductRequestDto request) {

        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {

        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(
            @PathVariable Long id) {

        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto request) {

        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        productService.delete(id);

        return "Product Deleted Successfully";
    }
}