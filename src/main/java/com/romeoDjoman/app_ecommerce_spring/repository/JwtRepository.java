package com.romeoDjoman.app_ecommerce_spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.romeoDjoman.app_ecommerce_spring.entity.Jwt;

import java.util.Optional;
import java.util.stream.Stream;

public interface JwtRepository extends CrudRepository<Jwt, Integer> {


    Optional<Jwt> findByValueAndDesactiveAndExpire(String value, boolean desactive, boolean expire);
    @Query("FROM Jwt j WHERE j.expire = :expire AND j.desactive = :desactive AND j.user.email = :email")
    Optional<Jwt> findUserValidToken(String email, boolean desactive, boolean expire);

    @Query("FROM Jwt j WHERE j.user.email = :email")
    Stream<Jwt> findUser(String email);

    @Query("FROM Jwt j WHERE j.refreshToken.value = :value")
    Optional<Jwt> findByRefreshToken(String value);

    void deleteAllByExpireAndDesactive(boolean expire, boolean desactive);



}
