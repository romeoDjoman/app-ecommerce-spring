package com.romeoDjoman.app_ecommerce_spring.controller;

import com.romeoDjoman.app_ecommerce_spring.entity.Article;
import com.romeoDjoman.app_ecommerce_spring.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "publication")
public class PublicationController {

    private final ArticleService articleService;

    @PostMapping(path = "create-article")
    public @ResponseBody Article article(@RequestBody Article articleRequest) {
        return this.articleService.createArticle(articleRequest);
    }
}
