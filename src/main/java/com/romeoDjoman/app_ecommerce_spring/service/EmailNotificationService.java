package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailNotificationService {

    JavaMailSender javaMailSender;
    public void send(EmailValidation emailValidation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@inscic.com");
        message.setTo(emailValidation.getUser().getEmail());
        message.setSubject("Votre code d'activation");

        String text = String.format(
                "Bonjour %s, <br /> Votre code d'activation est %s; A bientôt",
                emailValidation.getUser().getName(),
                emailValidation.getCode()
                );
        message.setText(text);

        javaMailSender.send(message);

    }
}
