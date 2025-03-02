package com.romeoDjoman.app_ecommerce_spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private Double totalPrice = 0.0;


    public Cart(User user) {
        this.user = user;
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(CartItem item) {
        this.items.add(item);
        this.totalPrice += item.getPrice() * item.getQuantity();
    }
}
