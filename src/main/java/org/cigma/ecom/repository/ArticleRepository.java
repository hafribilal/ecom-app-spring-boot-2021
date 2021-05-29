package org.cigma.ecom.repository;

import org.cigma.ecom.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    List<Article> findByTitreContainsOrDescriptionContains(String titre, String description);
    List<Article> findArticlesByTitreContains(String search);
    Page<Article> findByTitreContainsOrDescriptionContains(String titre, String description, Pageable p);
    Page<Article> findArticlesByTitreContains(String search, Pageable p);
}
