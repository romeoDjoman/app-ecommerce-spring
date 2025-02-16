package com.romeoDjoman.app_ecommerce_spring.repository;

import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailValidationRepository extends CrudRepository<EmailValidation, Integer> {

    Optional<EmailValidation> findByCode(String code);
}
