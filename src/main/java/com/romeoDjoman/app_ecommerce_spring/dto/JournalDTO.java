package com.romeoDjoman.app_ecommerce_spring.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class JournalDTO extends PublicationDTO {
    @NotBlank(message = "Le volume est obligatoire")
    private String volume;
    @NotBlank(message = "Le numéro est obligatoire")
    private String issue;
    @NotNull(message = "L'année est obligatoire")
    private Integer year;
    @NotBlank(message = "L'ISSN est obligatoire")
    private String issn;
    @NotBlank(message = "L'éditeur est obligatoire")
    private String editor;
    private Double impactFactor;
    @NotBlank(message = "La fréquence est obligatoire")
    private String frequency;
    @NotNull(message = "Le statut d'abonnement est obligatoire")
    private Boolean subscribed;
    private String publisherLocation;
    @NotNull(message = "La vérification par les pairs est obligatoire")
    private Boolean peerReview;
    private List<String> editorialBoard;
}
