package org.cigma.ecom.service;

import org.cigma.ecom.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleService {
    Article insertArticle(Article a, String username);

    Article updateArticle(Article a, String username);

    void deleteArticle(int id, String username);

    Article selectOne(int id);

    List<Article> selectAll();

    List<Article> selectByUser(String username);

    Page<Article> getPage(Pageable p);

    List<Article> search(String search);

    Page<Article> search(String search, Pageable p);
}
