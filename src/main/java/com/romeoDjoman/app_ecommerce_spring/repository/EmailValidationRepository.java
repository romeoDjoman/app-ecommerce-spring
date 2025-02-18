package com.romeoDjoman.app_ecommerce_spring.repository;

import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface EmailValidationRepository extends CrudRepository<EmailValidation, Integer> {

    Optional<EmailValidation> findByCode(String code);

    Optional<EmailValidation> findByUser(User user);


    void deleteAllByExpirationBefore(Instant now);
}
