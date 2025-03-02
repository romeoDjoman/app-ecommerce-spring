package com.romeoDjoman.app_ecommerce_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romeoDjoman.app_ecommerce_spring.dto.AuthenticationDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.AuthenticationResponseDTO;
import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.security.JwtService;
import com.romeoDjoman.app_ecommerce_spring.service.EmailValidationService;
import com.romeoDjoman.app_ecommerce_spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailValidationService emailValidationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            userService.signup(user);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error("Erreur lors de l'inscription : {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate-email")
    public ResponseEntity<?> validateEmail(@RequestBody Map<String, String> request) {
        try {
            String code = request.get("code");
            EmailValidation emailValidation = emailValidationService.readTheCode(code);
            if (emailValidation.getExpiration().isAfter(Instant.now())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Code expiré");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Code invalide");
        }
    }


    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation) {
        this.userService.activation(activation);
    }

    @PostMapping(path = "refresh-token")
    public @ResponseBody Map<String, String> refreshToken(@RequestBody Map<String, String> refreshTokenRequest) {
        return this.jwtService.refreshToken(refreshTokenRequest);
    }

    @PostMapping(path = "change-password")
    public void changePassword(@RequestBody Map<String, String> activation) {
        this.userService.changePassword(activation);
    }

    @PostMapping(path = "new-password")
    public void newPassword(@RequestBody Map<String, String> activation) {
        this.userService.newPassword(activation);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password())
            );

            if (authentication.isAuthenticated()) {
                Map<String, String> tokens = jwtService.generate(authenticationDTO.username());
                User user = (User) authentication.getPrincipal();
                AuthenticationResponseDTO response = AuthenticationResponseDTO.builder()
                        .bearer(tokens.get(JwtService.BEARER))
                        .refresh(tokens.get(JwtService.REFRESH))
                        .user(user)
                        .build();
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            log.error("Erreur lors de la connexion : {}", e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping(path = "logout")
    public void logout() {
        this.jwtService.logout();
    }
}
