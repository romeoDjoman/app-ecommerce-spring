package com.romeoDjoman.app_ecommerce_spring.service;

import org.springframework.stereotype.Service;

import com.romeoDjoman.app_ecommerce_spring.entity.Reviews;
import com.romeoDjoman.app_ecommerce_spring.repository.ReviewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public void create(Reviews reviews) {
        this.reviewsRepository.save(reviews);
    }


}
