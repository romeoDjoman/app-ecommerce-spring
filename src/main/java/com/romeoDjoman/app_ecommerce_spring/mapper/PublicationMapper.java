package com.romeoDjoman.app_ecommerce_spring.mapper;

import com.romeoDjoman.app_ecommerce_spring.dto.ArticleDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.JournalDTO;
import com.romeoDjoman.app_ecommerce_spring.dto.PublicationDTO;
import com.romeoDjoman.app_ecommerce_spring.entity.Article;
import com.romeoDjoman.app_ecommerce_spring.entity.Journal;
import com.romeoDjoman.app_ecommerce_spring.entity.Publication;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapper {

    public Publication toEntity(PublicationDTO dto) {
        if (dto instanceof ArticleDTO) {
            return toArticleEntity((ArticleDTO) dto);
        } else if (dto instanceof JournalDTO) {
            return toJournalEntity((JournalDTO) dto);
        }
        throw new IllegalArgumentException("Type de DTO non supporté : " + dto.getClass().getSimpleName());
    }

    private Article toArticleEntity(ArticleDTO dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setPrice(dto.getPrice());
        article.setCategory(dto.getCategory());
        article.setPublicationDate(dto.getPublicationDate());
        article.setPublisher(dto.getPublisher());
        article.setLanguage(dto.getLanguage());
        article.setIsbn(dto.getIsbn());
        article.setPageCount(dto.getPageCount());
        article.setAvailable(dto.getAvailable());
        article.setRating(dto.getRating());

        article.setAuthors(dto.getAuthors());
        article.setDoi(dto.getDoi());
        article.setKeyword(dto.getKeyword());
        article.setCoverImageA(dto.getCoverImageA());
        article.setSubmissionDate(dto.getSubmissionDate());
        article.setAcceptanceDate(dto.getAcceptanceDate());
        article.setRevisionDate(dto.getRevisionDate());
        article.setStatus(dto.getStatus());
        article.setImpactFactor(dto.getImpactFactor());
        article.setCitationCount(dto.getCitationCount());

        return article;
    }

    private Journal toJournalEntity(JournalDTO dto) {
        Journal journal = new Journal();
        journal.setTitle(dto.getTitle());
        journal.setDescription(dto.getDescription());
        journal.setPrice(dto.getPrice());
        journal.setCategory(dto.getCategory());
        journal.setPublicationDate(dto.getPublicationDate());
        journal.setPublisher(dto.getPublisher());
        journal.setLanguage(dto.getLanguage());
        journal.setIsbn(dto.getIsbn());
        journal.setPageCount(dto.getPageCount());
        journal.setAvailable(dto.getAvailable());
        journal.setRating(dto.getRating());

        journal.setVolume(dto.getVolume());
        journal.setIssue(dto.getIssue());
        journal.setYear(dto.getYear());
        journal.setIssn(dto.getIssn());
        journal.setEditor(dto.getEditor());
        journal.setImpactFactor(dto.getImpactFactor());
        journal.setFrequency(dto.getFrequency());
        journal.setSubscribed(dto.getSubscribed());
        journal.setPublisherLocation(dto.getPublisherLocation());
        journal.setPeerReview(dto.getPeerReview());
        journal.setEditorialBoard(dto.getEditorialBoard());

        return journal;
    }

    public PublicationDTO toDto(Publication publication) {
        if (publication instanceof Article) {
            return toArticleDto((Article) publication);
        } else if (publication instanceof Journal) {
            return toJournalDto((Journal) publication);
        }
        throw new IllegalArgumentException("Type d'entité non supporté : " + publication.getClass().getSimpleName());
    }

    private ArticleDTO toArticleDto(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(article.getTitle());
        dto.setDescription(article.getDescription());
        dto.setPrice(article.getPrice());
        dto.setCategory(article.getCategory());
        dto.setPublicationDate(article.getPublicationDate());
        dto.setPublisher(article.getPublisher());
        dto.setLanguage(article.getLanguage());
        dto.setIsbn(article.getIsbn());
        dto.setPageCount(article.getPageCount());
        dto.setAvailable(article.getAvailable());
        dto.setRating(article.getRating());

        dto.setAuthors(article.getAuthors());
        dto.setDoi(article.getDoi());
        dto.setKeyword(article.getKeyword());
        dto.setCoverImageA(article.getCoverImageA());
        dto.setSubmissionDate(article.getSubmissionDate());
        dto.setAcceptanceDate(article.getAcceptanceDate());
        dto.setRevisionDate(article.getRevisionDate());
        dto.setStatus(article.getStatus());
        dto.setImpactFactor(article.getImpactFactor());
        dto.setCitationCount(article.getCitationCount());

        if(article.getJournal() != null) {
            dto.setJournalId(article.getJournal().getId());
        }
        return dto;
    }

    private JournalDTO toJournalDto(Journal journal) {
        JournalDTO dto = new JournalDTO();
        dto.setTitle(journal.getTitle());
        dto.setDescription(journal.getDescription());
        dto.setPrice(journal.getPrice());
        dto.setCategory(journal.getCategory());
        dto.setPublicationDate(journal.getPublicationDate());
        dto.setPublisher(journal.getPublisher());
        dto.setLanguage(journal.getLanguage());
        dto.setIsbn(journal.getIsbn());
        dto.setPageCount(journal.getPageCount());
        dto.setAvailable(journal.getAvailable());
        dto.setRating(journal.getRating());

        dto.setVolume(journal.getVolume());
        dto.setIssue(journal.getIssue());
        dto.setYear(journal.getYear());
        dto.setIssn(journal.getIssn());
        dto.setEditor(journal.getEditor());
        dto.setImpactFactor(journal.getImpactFactor());
        dto.setFrequency(journal.getFrequency());
        dto.setSubscribed(journal.getSubscribed());
        dto.setPublisherLocation(journal.getPublisherLocation());
        dto.setPeerReview(journal.getPeerReview());
        dto.setEditorialBoard(journal.getEditorialBoard());

        return dto;
    }
}
