package com.romeoDjoman.app_ecommerce_spring.controller;

import com.romeoDjoman.app_ecommerce_spring.dto.CartDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.CartItemDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.PublicationDTO;
import com.romeoDjoman.app_ecommerce_spring.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getCart(@RequestParam Long userId) {
        List<CartItemDTO> cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addPublicationToCart(
            @RequestParam Long userId,
            @RequestParam Long publicationId,
            @RequestParam Integer quantity) {
        CartDTO updatedCart = cartService.addPublicationToCart(userId, publicationId, quantity);
        return ResponseEntity.ok(updatedCart);
    }



}
