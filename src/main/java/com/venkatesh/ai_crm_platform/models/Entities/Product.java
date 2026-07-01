package com.venkatesh.ai_crm_platform.models.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;
    private String imageUrl;
    private Integer stock;
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
}