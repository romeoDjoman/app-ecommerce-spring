package com.romeoDjoman.app_ecommerce_spring.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Journal")
public class Journal extends Publication {
    private String volume;
    private String issue;
    private Integer year;

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL)
    private List<Article> articles;

    private String issn;
    private String editor;
    private Double impactFactor;
    private String frequency;
    private Boolean subscribed;
    private String publisherLocation;
    private Boolean peerReview;
    private List<String> editorialBoard;

    
}
