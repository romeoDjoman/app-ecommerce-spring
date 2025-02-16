package com.romeoDjoman.app_ecommerce_spring.controller;

import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @PostMapping(path = "signup")
    public void signup(@RequestBody User user) {
        log.info("Inscription");
        this.userService.signup(user);
    }

    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation) {
        this.userService.activation(activation);
    }
}
