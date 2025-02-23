package com.romeoDjoman.app_ecommerce_spring.repository;

import com.romeoDjoman.app_ecommerce_spring.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}

