
package com.venkatesh.ai_crm_platform.dto.product;

import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;
}