package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.entity.EmailValidation;
import com.romeoDjoman.app_ecommerce_spring.entity.Role;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.enums.RoleType;
import com.romeoDjoman.app_ecommerce_spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private EmailValidationService emailValidationService;
    private BCryptPasswordEncoder passwordEncoder;

    public void signup(User user) {
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Votre mail est invalide");
        }
        if (!user.getEmail().contains(".")) {
            throw new RuntimeException("Votre mail est invalide");
        }

        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Votre mail est déjà utilisé");
        }
        String pwdCrypted = this.passwordEncoder.encode(user.getPwd());
        user.setPwd(pwdCrypted);

        Role roleUser = new Role();
        roleUser.setLabel(RoleType.USER);
        user.setRole(roleUser);

        user = this.userRepository.save(user);
        this.emailValidationService.save(user);
    }

    public void activation(Map<String, String> activation) {
        EmailValidation emailValidation = this.emailValidationService.readTheCode(activation.get("code"));

        if (Instant.now().isAfter(emailValidation.getExpiration())) {
            throw new RuntimeException("Votre code a expiré");
        }
        User userActivated = this.userRepository.findById(emailValidation.getUser().getId()).orElseThrow(() -> new RuntimeException("Utilisateur inconnu"));
        userActivated.setActive(true);
        this.userRepository.save(userActivated);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne correspond à cet identitifant"));
    }
}