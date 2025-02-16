package com.romeoDjoman.app_ecommerce_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.romeoDjoman.app_ecommerce_spring.entity.Reviews;
import com.romeoDjoman.app_ecommerce_spring.service.ReviewsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("reviews")
@RestController
public class ReviewsController {
    private final ReviewsService reviewsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Reviews reviews) {
        this.reviewsService.create(reviews);
    }
}
