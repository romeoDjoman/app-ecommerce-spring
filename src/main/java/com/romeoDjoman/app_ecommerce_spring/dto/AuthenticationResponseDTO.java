package com.romeoDjoman.app_ecommerce_spring.dto;

import com.romeoDjoman.app_ecommerce_spring.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponseDTO {
    String bearer;
    String refresh;
    User user;
}
