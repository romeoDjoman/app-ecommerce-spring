package com.romeoDjoman.app_ecommerce_spring.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "publicationType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArticleDTO.class, name = "Article"),
        @JsonSubTypes.Type(value = JournalDTO.class, name = "Journal")
})

@Data
@DiscriminatorColumn(name = "publication_type", discriminatorType = DiscriminatorType.STRING)
public class PublicationDTO {
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    private Double price;

    @NotBlank(message = "La catégorie est obligatoire")
    private String category;

    @NotNull(message = "La date de publication est obligatoire")
    private LocalDate publicationDate;

    @NotBlank(message = "L'éditeur est obligatoire")
    private String publisher;

    @NotBlank(message = "La langue est obligatoire")
    private String language;

    @NotBlank(message = "L'ISBN est obligatoire")
    private String isbn;

    @NotNull(message = "Le nombre de pages est obligatoire")
    private Integer pageCount;

    @NotNull(message = "La disponibilité est obligatoire")
    private Boolean available;

    private Double rating;

    private Integer salesCount = 0;


    public void incrementSalesCount() {
        this.salesCount++;
    }

    private Long authorId;

    // Je dois ajouter à la methode OrderProcess pour incrémenter le nombre de ventes.
    /*public void processOrder(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            Publication publication = item.getPublication(); // Publication vendue
            publication.incrementSalesCount();  // Incrémente le compteur de ventes
            publicationRepository.save(publication); // Sauvegarde la publication mise à jour
        }
        orderRepository.save(order); // Sauvegarde la commande
    }*/
}
