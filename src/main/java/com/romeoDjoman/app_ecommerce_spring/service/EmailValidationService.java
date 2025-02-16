package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.repository.EmailValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
public class EmailValidationService {
    private EmailValidationRepository emailValidationRepository;
    private EmailNotificationService emailNotificationService;

    public void save(User user) {
        EmailValidation emailValidation = new EmailValidation();
        emailValidation.setUser(user);

        Instant creation = Instant.now();
        Instant expiration = Instant.now();
        emailValidation.setCreation(creation);
        expiration = creation.plus(10, MINUTES);
        emailValidation.setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);

        emailValidation.setCode(code );
        this.emailValidationRepository.save(emailValidation);
        this.emailNotificationService.send(emailValidation);
    }

    public EmailValidation readTheCode(String code) {
        return this.emailValidationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }
}
