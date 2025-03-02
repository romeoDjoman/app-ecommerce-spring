package com.romeoDjoman.app_ecommerce_spring.dto;

import com.romeoDjoman.app_ecommerce_spring.entity.CartItem;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemDTO> items = new ArrayList<>();

    private Double totalPrice = 0.0;
}

