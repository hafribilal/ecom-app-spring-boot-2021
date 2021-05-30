package org.cigma.ecom.controller;

import org.cigma.ecom.model.Article;
import org.cigma.ecom.service.IArticleService;
import org.cigma.ecom.util.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/articles")
public class ArticleController {
    @Autowired
    IArticleService service;
    @Autowired
    CheckUser checkUser;

    @GetMapping(params = {"pageNumber", "pageSize"})
    public Page<Article> getPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.getPage(pageParams);
    }

    @GetMapping(path = "/search", params = {"q", "pageNumber", "pageSize"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Article> search(@RequestParam("q") String search, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.search(search,pageParams);
    }

    @GetMapping(path = "/search", params = { "q" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> search(@RequestParam("q") String search){
        return service.search(search);
    }

    @GetMapping(path="/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public Article getOne(@PathVariable("id") int id) {
        return service.selectOne(id);
    }

    @GetMapping(path = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getAll(){
        return service.selectAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Article add(@RequestBody Article a, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return service.insertArticle(a, username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Article update(@RequestBody Article a, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return service.updateArticle(a, username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable("id") int id, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        service.deleteArticle(id, username);
    }
}
