package com.romeoDjoman.app_ecommerce_spring.dto;

import com.romeoDjoman.app_ecommerce_spring.entity.Cart;
import com.romeoDjoman.app_ecommerce_spring.entity.Publication;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {
    private Long id;
    private Long publicationId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;

    private Integer quantity;
    private Double price;
}
