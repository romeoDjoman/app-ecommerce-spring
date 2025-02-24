package com.romeoDjoman.app_ecommerce_spring.dto;

import com.romeoDjoman.app_ecommerce_spring.entity.Figure;
import com.romeoDjoman.app_ecommerce_spring.entity.Reference;
import com.romeoDjoman.app_ecommerce_spring.entity.TableArticle;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class ArticleDTO extends PublicationDTO {
    @NotBlank(message = "Les auteurs sont obligatoires")
    private String authors;
    private String doi;
    private String keyword;
    private byte[] coverImageA;
    private List<Figure> figures;
    private List<Reference> references;
    private List<TableArticle> tables;
    private LocalDate submissionDate;
    private LocalDate acceptanceDate;
    private LocalDate revisionDate;
    private String status;
    private Long journalId; // Référence au journal
    private Double impactFactor;
    private Integer citationCount;
    private Long authorId;
}
