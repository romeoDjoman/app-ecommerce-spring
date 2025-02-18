package com.romeoDjoman.app_ecommerce_spring.controller;

import com.romeoDjoman.app_ecommerce_spring.dto.AuthenticationDTO;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.security.JwtService;
import com.romeoDjoman.app_ecommerce_spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

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
    public  Map<String, String> login(@RequestBody AuthenticationDTO authenticationDTO) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password())
        );

        if(authentication.isAuthenticated()) {
            return this.jwtService.generate(authenticationDTO.username());
        }
        return null;
    }

    @PostMapping(path = "logout")
    public void logout() {
        this.jwtService.logout();
    }
}
