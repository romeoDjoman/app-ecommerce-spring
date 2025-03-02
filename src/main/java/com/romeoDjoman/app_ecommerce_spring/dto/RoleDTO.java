package com.romeoDjoman.app_ecommerce_spring.dto;

import com.romeoDjoman.app_ecommerce_spring.enums.RoleType;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private RoleType label;
}
