package com.romeoDjoman.app_ecommerce_spring.controller;

import com.romeoDjoman.app_ecommerce_spring.dto.PublicationDTO;
import com.romeoDjoman.app_ecommerce_spring.service.PublicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "publications")
public class PublicationController {

    private final PublicationService publicationService;

    @PostMapping
    public ResponseEntity<PublicationDTO> createPublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        PublicationDTO createdPublication = publicationService.createPublication(publicationDTO);
        return ResponseEntity
                .created(URI.create("/publications/" + createdPublication.getId()))
                .body(createdPublication);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PublicationDTO>> searchPublicationsByKeyword(@RequestParam String keyword) {
        List<PublicationDTO> publications = publicationService.searchPublicationsByKeyword(keyword);
        return ResponseEntity.ok(publications);
    }











    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(jakarta.persistence.EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
