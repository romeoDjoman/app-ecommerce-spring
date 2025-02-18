package com.romeoDjoman.app_ecommerce_spring.security;


import com.romeoDjoman.app_ecommerce_spring.entity.Jwt;
import com.romeoDjoman.app_ecommerce_spring.entity.RefreshToken;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.repository.JwtRepository;
import com.romeoDjoman.app_ecommerce_spring.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@AllArgsConstructor
@Service
public class JwtService {
    public static final String BEARER = "bearer";
    public static final String TOKEN_INVALIDE = "Token invalide";
    public static final String REFRESH = "refresh";
    private final String ENCRYPTION_KEY = "c0c786b5ad1731e3dd6231579815127a727fb3c1e72b75570779cf4abf43f69e";
    private UserService userService;
    private JwtRepository jwtRepository;

    public Jwt tokenByValue(String value) {
        return this.jwtRepository.findByValueAndDesactiveAndExpire(
                        value,
                        false,
                        false)
                .orElseThrow(() -> new RuntimeException("Token invalide ou inconnu"));
    }

    public Map<String, String> generate(String username) {
        User user = (User) this.userService.loadUserByUsername(username);
        this.disableTokens(user);
        final Map<String, String> jwtMap = new java.util.HashMap<>(this.generateJwt(user));

        RefreshToken refreshToken = RefreshToken.builder()
                .value(UUID.randomUUID().toString())
                .expire(false)
                .creation(Instant.now())
                .expiration(Instant.now().plusMillis(30 * 60 * 1000))
                .build();

        final Jwt jwt = Jwt
                .builder()
                .value(jwtMap.get(BEARER))
                .desactive(false)
                .expire(false)
                .user(user)
                .refreshToken(refreshToken)
                .build();

        this.jwtRepository.save(jwt);
        jwtMap.put(REFRESH, refreshToken.getValue());
        return jwtMap;
    }

    private void disableTokens(User user) {
        final List<Jwt> jwtList = this.jwtRepository.findUser(user.getEmail()).peek(
                jwt -> {
                    jwt.setDesactive(true);
                    jwt.setExpire(true);
                }
        ).collect(Collectors.toList());

        this.jwtRepository.saveAll(jwtList);
    }


    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaim(token, Claims::getExpiration);
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



    private Map<String, String> generateJwt(User user) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 60 * 1000;

        final Map<String, Object> claims = Map.of(
                "name", user.getName(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getEmail()

        );


        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer", bearer);

    }

    public void logout() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Jwt jwt = this.jwtRepository.findUserValidToken(
                user.getEmail(),
                false,
                false
        ).orElseThrow(() -> new RuntimeException(TOKEN_INVALIDE));
        jwt.setExpire(true);
        jwt.setDesactive(true);
        this.jwtRepository.save(jwt);
    }

//    @Scheduled(cron = "0 */1 * * * *") // Supprimer chaque minute, voire CronTab
//    public void removeUselessJwt(){
//        log.info("Suppression des tokens à {}", Instant.now());
//        this.jwtRepository.deleteAllByExpireAndDesactive(true, true);
//    }

    public Map<String, String> refreshToken(Map<String, String> refreshTokenRequest) {
        final Jwt jwt = this.jwtRepository.findByRefreshToken(refreshTokenRequest.get(REFRESH)).orElseThrow(() -> new RuntimeException(TOKEN_INVALIDE));
        if(jwt.getRefreshToken().isExpire() || jwt.getRefreshToken().getExpiration().isBefore(Instant.now())) {
            throw new RuntimeException(TOKEN_INVALIDE);
        }
        this.disableTokens(jwt.getUser());
        return this.generate(jwt.getUser().getEmail());
    }

}
