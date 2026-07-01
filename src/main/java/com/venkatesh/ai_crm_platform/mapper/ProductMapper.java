package com.venkatesh.ai_crm_platform.mapper;

import com.venkatesh.ai_crm_platform.dto.product.ProductRequestDto;
import com.venkatesh.ai_crm_platform.dto.product.ProductResponseDto;
import com.venkatesh.ai_crm_platform.models.Entities.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    // DTO -> Entity
    public static Product toEntity(ProductRequestDto dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return product;
    }

    // Entity -> DTO
    public static ProductResponseDto toResponse(Product product) {

        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setImageUrl(product.getImageUrl());

        return dto;
    }
}