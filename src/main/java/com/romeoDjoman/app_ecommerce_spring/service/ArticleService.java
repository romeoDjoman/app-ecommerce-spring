package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.entity.Article;
import com.romeoDjoman.app_ecommerce_spring.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    public Article createArticle(Article article) {
        this.articleRepository.save(article);
        return article;
    }

}
