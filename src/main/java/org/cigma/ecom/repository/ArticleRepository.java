package org.cigma.ecom.repository;

import org.cigma.ecom.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByTitreContainsOrDescriptionContains(String titre, String description);

    List<Article> findArticlesByTitreContains(String search);

    Page<Article> findByTitreContainsOrDescriptionContains(String titre, String description, Pageable p);

    Page<Article> findArticlesByTitreContains(String search, Pageable p);

    @Query("select case when count(p)> 0 then true else false end from Panier p where lower(p.article.id) like lower(:idArticle)")
    boolean existsArticleOnPanier(@Param("idArticle") int id);

    //Administrateur findAdministrateurByUsername(String username);

}
