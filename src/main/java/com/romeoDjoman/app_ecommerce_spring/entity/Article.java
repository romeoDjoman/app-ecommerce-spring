package com.romeoDjoman.app_ecommerce_spring.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Article")
public class Article extends Publication {
    private String authors;
    private String doi;
    private String keyword;

    @Lob
    private byte[] coverImageA;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Figure> figures;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Reference> references;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<TableArticle> tables;

    private LocalDate submissionDate;
    private LocalDate acceptanceDate;
    private LocalDate revisionDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

    private Double impactFactor;
    private Integer citationCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
}
