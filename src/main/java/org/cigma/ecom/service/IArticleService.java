package org.cigma.ecom.service;

import org.cigma.ecom.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleService {
    Article insertArticle(Article a);
    Article updateArticle(Article a);
    void deleteArticle(int id);
    Article selectOne(int id);
    List<Article> selectAll();
    Page<Article> getPage(Pageable p);
    List<Article> search(String search);
    Page<Article> search(String search, Pageable p);
}