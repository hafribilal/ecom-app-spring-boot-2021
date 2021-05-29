package org.cigma.ecom.controller;

import org.cigma.ecom.model.Panier;
import org.cigma.ecom.service.IPanierService;
import org.cigma.ecom.util.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/panier")
public class PanierController {
    @Autowired
    IPanierService service;
    @Autowired
    CheckUser checkUser;

    @GetMapping(params = { "pageNumber", "pageSize" })
    public Page<Panier> getPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,@RequestHeader("Authorization")String auth) {
        String username = checkUser.getUsername(auth);
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.getPage(pageParams, username);
    }

    @GetMapping(path="/{id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public Panier getOne(@PathVariable("id") int id,@RequestHeader("Authorization")String auth) {
        String username = checkUser.getUsername(auth);
        return service.selectOne(id, username);
    }

    @GetMapping(path = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Panier> getAll(@RequestHeader("Authorization")String auth){
        String username = checkUser.getUsername(auth);
        return service.selectAll(username);
    }

    @PostMapping(path="/create",produces=MediaType.APPLICATION_JSON_VALUE)
    public Panier add( @RequestBody Panier p,@RequestHeader("Authorization")String auth) {
        String username = checkUser.getUsername(auth);
        return service.insertPanier(p,username);
    }

    @PutMapping(path="/update",produces=MediaType.APPLICATION_JSON_VALUE,
            consumes=MediaType.APPLICATION_JSON_VALUE)
    public Panier update( @RequestBody Panier p,@RequestHeader("Authorization")String auth) {
        String username = checkUser.getUsername(auth);
        return service.updatePanier(p,username);
    }

    @DeleteMapping(path="/{id}")
    public void deleteOne(@PathVariable("id") int id,@RequestHeader("Authorization")String auth) {
        String username = checkUser.getUsername(auth);
        service.deletePanier(id,username);
    }
}