package org.cigma.ecom.controller;

import org.cigma.ecom.model.Article;
import org.cigma.ecom.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/articles")
public class ArticleController {
    @Autowired
    IArticleService service;

    @GetMapping(params = { "pageNumber", "pageSize" })
    public Page<Article> getPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.getPage(pageParams);
    }

    @GetMapping(path="/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public Article getOne(@PathVariable("id") int id) {
        return service.selectOne(id);
    }

    @GetMapping(path = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getAll(){
        return service.selectAll();
    }

    @PostMapping(path="/create",produces=MediaType.APPLICATION_JSON_VALUE)
    public Article add( @RequestBody Article a) {
        return service.insertArticle(a);
    }

    @PutMapping(path="/update",produces=MediaType.APPLICATION_JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE)
    public Article update( @RequestBody Article a) {
        return service.updateArticle(a);
    }

    @DeleteMapping(path="/{id}")
    public void deleteOne(@PathVariable("id") int id) {
        service.deleteArticle(id);
    }
}
