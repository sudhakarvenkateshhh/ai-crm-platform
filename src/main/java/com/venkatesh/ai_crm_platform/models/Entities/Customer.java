package com.venkatesh.ai_crm_platform.models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    private String phone;

    private String companyName;

    private String address;
    private String profileImage;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sales_person_id")
    private User assignedSalesPerson;
}