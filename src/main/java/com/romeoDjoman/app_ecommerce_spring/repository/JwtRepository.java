package com.romeoDjoman.app_ecommerce_spring.repository;

import org.springframework.data.repository.CrudRepository;
import com.romeoDjoman.app_ecommerce_spring.entity.Jwt;

public interface JwtRepository extends CrudRepository<Jwt, Integer> { }
