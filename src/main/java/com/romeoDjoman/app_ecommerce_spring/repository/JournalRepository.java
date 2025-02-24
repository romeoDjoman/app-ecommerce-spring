package com.romeoDjoman.app_ecommerce_spring.repository;

import com.romeoDjoman.app_ecommerce_spring.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}