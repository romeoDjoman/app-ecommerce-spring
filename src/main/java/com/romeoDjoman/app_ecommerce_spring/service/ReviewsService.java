package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.romeoDjoman.app_ecommerce_spring.entity.Reviews;
import com.romeoDjoman.app_ecommerce_spring.repository.ReviewsRepository;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public void create(Reviews reviews) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reviews.setUser(user);
        this.reviewsRepository.save(reviews);
    }


    public List<Reviews> list() {
        return (List<Reviews>) this.reviewsRepository.findAll();
    }
}
