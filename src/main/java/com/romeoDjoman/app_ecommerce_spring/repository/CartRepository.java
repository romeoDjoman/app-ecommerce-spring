package com.romeoDjoman.app_ecommerce_spring.repository;

import com.romeoDjoman.app_ecommerce_spring.entity.Cart;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
