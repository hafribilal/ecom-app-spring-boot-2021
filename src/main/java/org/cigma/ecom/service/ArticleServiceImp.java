package org.cigma.ecom.service;

import org.cigma.ecom.model.Article;
import org.cigma.ecom.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImp implements IArticleService{
    @Autowired
    ArticleRepository repository;

    @Override
    public Article insertArticle(Article a) {
        if (a.getStock() > 0)
            return repository.save(a);
        return null;
    }

    @Override
    public Article updateArticle(Article a) {
        Article old = repository.findById(a.getId()).get();
        old.setTitre(a.getTitre());
        old.setDescription(a.getDescription());
        if (a.getStock() > 0)
            old.setStock(a.getStock());
        else old.setStock(1);
        old.setType(a.getType());
        old.setVendeur(a.getVendeur());
        return repository.save(old);
    }

    @Override
    public void deleteArticle(int id) {
        if (repository.existsArticleOnPanier(id))
            repository.deleteById(id);
    }

    @Override
    public Article selectOne(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Article> selectAll() {
        return (List<Article>) repository.findAll();
    }

    @Override
    public Page<Article> getPage(Pageable p) {
        return repository.findAll(p);
    }

    @Override
    public List<Article> search(String search) {
        //return repository.findArticlesByTitreContains(search);
        return repository.findByTitreContainsOrDescriptionContains(search,search);
    }

    @Override
    public Page<Article> search(String search, Pageable p) {
        //return repository.findArticlesByTitreContains(search, p);
        return repository.findByTitreContainsOrDescriptionContains(search, search, p);
    }

}
