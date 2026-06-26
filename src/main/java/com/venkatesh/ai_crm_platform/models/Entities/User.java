package com.venkatesh.ai_crm_platform.models.Entities;

import com.venkatesh.ai_crm_platform.models.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active = true;
}