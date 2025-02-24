package com.romeoDjoman.app_ecommerce_spring.repository;

import com.romeoDjoman.app_ecommerce_spring.entity.Article;
import com.romeoDjoman.app_ecommerce_spring.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long>, JpaSpecificationExecutor<Publication> {
    List<Publication> findByTitleContainingOrDescriptionContaining(String title, String description);
}
