package com.romeoDjoman.app_ecommerce_spring.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "publication_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "publication")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "publicationType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Article.class, name = "Article"),
        @JsonSubTypes.Type(value = Journal.class, name = "Journal")
})
public abstract class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double price;
    private String category;
    private LocalDate publicationDate;
    private String publisher;
    private String language;
    private String isbn;
    private Integer pageCount;
    private Boolean available;
    private Double rating;
}
