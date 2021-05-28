package org.cigma.ecom.repository;

import org.cigma.ecom.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article,Integer> {
    //List<Article> findArticleByAdministrateur_Username(String username);
}
