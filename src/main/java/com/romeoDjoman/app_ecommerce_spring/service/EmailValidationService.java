package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import com.romeoDjoman.app_ecommerce_spring.repository.EmailValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Transactional
@Slf4j
@AllArgsConstructor
@Service
public class EmailValidationService {
    private EmailValidationRepository emailValidationRepository;
    private EmailNotificationService emailNotificationService;

    public void save(User user) {
        // Vérification si une validation existe déjà pour l'utilisateur
        Optional<EmailValidation> existingValidation = emailValidationRepository.findByUser(user);
        if (existingValidation.isPresent()) {
            // Si une validation existe déjà, vous pouvez soit la mettre à jour soit simplement la supprimer et en créer une nouvelle.
            EmailValidation existing = existingValidation.get();
            existing.setCode(generateCode());  // Régénère un code
            existing.setCreation(Instant.now());
            existing.setExpiration(Instant.now().plus(10, MINUTES));  // Prolonge l'expiration
            this.emailValidationRepository.save(existing);
            this.emailNotificationService.send(existing);
        } else {
            // Sinon, créer une nouvelle validation
            EmailValidation emailValidation = new EmailValidation();
            emailValidation.setUser(user);

            Instant creation = Instant.now();
            emailValidation.setCreation(creation);
            Instant expiration = creation.plus(10, MINUTES);
            emailValidation.setExpiration(expiration);

            String code = generateCode();
            emailValidation.setCode(code);

            this.emailValidationRepository.save(emailValidation);
            this.emailNotificationService.send(emailValidation);
        }
    }

    // Méthode utilitaire pour générer un code
    private String generateCode() {
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        return String.format("%06d", randomInteger);
    }


    public EmailValidation readTheCode(String code) {
        return this.emailValidationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }

    @Scheduled(cron = "0 0 * * * *") // Chaque heure
    public void cleanTable() {
        final Instant now = Instant.now();
        log.info("Suppression des token à {}", now);
        this.emailValidationRepository.deleteAllByExpirationBefore(now);
    }
}
