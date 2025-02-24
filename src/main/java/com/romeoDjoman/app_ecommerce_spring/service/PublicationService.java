package com.romeoDjoman.app_ecommerce_spring.service;

import com.romeoDjoman.app_ecommerce_spring.dto.ArticleDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.PublicationDTO;
import com.romeoDjoman.app_ecommerce_spring.entity.Article;
import com.romeoDjoman.app_ecommerce_spring.entity.Journal;
import com.romeoDjoman.app_ecommerce_spring.entity.Publication;
import com.romeoDjoman.app_ecommerce_spring.mapper.PublicationMapper;
import com.romeoDjoman.app_ecommerce_spring.repository.JournalRepository;
import com.romeoDjoman.app_ecommerce_spring.repository.PublicationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final JournalRepository journalRepository;
    private final PublicationMapper publicationMapper;

    @Transactional
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        Publication publication = publicationMapper.toEntity(publicationDTO);


        if (publication instanceof Article && publicationDTO instanceof ArticleDTO articleDTO) {
            if (articleDTO.getJournalId() != null) {
                Journal journal = journalRepository.findById(articleDTO.getJournalId())
                        .orElseThrow(() -> new EntityNotFoundException("Journal non trouvé avec l'id: " + articleDTO.getJournalId()));
                ((Article) publication).setJournal(journal);
            }
        }

        Publication savedPublication = publicationRepository.save(publication);
        return publicationMapper.toDto(savedPublication);
    }

    public List<PublicationDTO> searchPublicationsByKeyword(String keyword) {
        List<Publication> publications = publicationRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
        return publications.stream()
                .map(publicationMapper::toDto)
                .collect(Collectors.toList());
    }
}
