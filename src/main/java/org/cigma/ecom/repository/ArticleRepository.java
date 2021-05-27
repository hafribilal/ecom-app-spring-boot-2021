package org.cigma.ecom.repository;

import org.cigma.ecom.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Integer> {
}
