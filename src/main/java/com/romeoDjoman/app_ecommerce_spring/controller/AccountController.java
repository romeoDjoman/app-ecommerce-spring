package com.romeoDjoman.app_ecommerce_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romeoDjoman.app_ecommerce_spring.dto.AuthenticationDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.AuthenticationResponseDTO;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.security.JwtService;
import com.romeoDjoman.app_ecommerce_spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private UserService userService;
    private AuthenticationManager authenticationManager; 
    private JwtService jwtService;

    @PostMapping(path = "signup")
    public void signup(@RequestBody User user) {
        log.info("Inscription");
        this.userService.signup(user);
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

    @PostMapping(path = "login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password())
        );

        if(authentication.isAuthenticated()) {
            Map<String, String> tokens = this.jwtService.generate(authenticationDTO.username());
            User user = (User) authentication.getPrincipal();
            AuthenticationResponseDTO authenticationResponseDTO = AuthenticationResponseDTO.builder()
                    .bearer(tokens.get(JwtService.BEARER))
                    .refresh(tokens.get(JwtService.REFRESH))
                    .user(user)
                    .build();
            try {
                tokens.put("user", new ObjectMapper().writeValueAsString(user));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.status(200).body(authenticationResponseDTO);
        }
        return null;
    }

    @PostMapping(path = "logout")
    public void logout() {
        this.jwtService.logout();
    }
}
