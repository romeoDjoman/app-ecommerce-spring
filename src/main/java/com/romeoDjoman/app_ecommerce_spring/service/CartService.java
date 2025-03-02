package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.dto.CartDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.CartItemDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.PublicationDTO;
import com.romeoDjoman.app_ecommerce_spring.entity.Cart;
import com.romeoDjoman.app_ecommerce_spring.entity.CartItem;
import com.romeoDjoman.app_ecommerce_spring.entity.Publication;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.repository.CartRepository;
import com.romeoDjoman.app_ecommerce_spring.repository.PublicationRepository;
import com.romeoDjoman.app_ecommerce_spring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;


    public CartDTO addPublicationToCart(Long userId, Long publicationId, Integer quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new EntityNotFoundException("Publication not found with id: " + publicationId));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setPublication(publication);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(publication.getPrice() * quantity);

        cart.getItems().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());

        Cart savedCart = cartRepository.save(cart);
        return mapToCartDTO(savedCart);
    }

    private CartDTO mapToCartDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId((long) cart.getUser().getId())
                .items(cart.getItems().stream()
                        .map(this::mapToCartItemDTO)
                        .collect(Collectors.toList()))
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .id(cartItem.getId())
                .publicationId(cartItem.getPublication().getId())
                .publication(cartItem.getPublication())
                .cart(cartItem.getCart())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }


    public List<CartItemDTO> getCart(Long userId){
        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Panier non trouvé pour l'Id : " + userId));
        return cart.getItems().stream().map(this::mapToCartItemDTO).toList();
    }
}