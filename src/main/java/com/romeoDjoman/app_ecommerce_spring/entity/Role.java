package com.romeoDjoman.app_ecommerce_spring.entity;

import com.romeoDjoman.app_ecommerce_spring.enums.RoleType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleType label;
}
