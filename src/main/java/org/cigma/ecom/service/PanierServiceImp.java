package org.cigma.ecom.service;

import org.cigma.ecom.model.Panier;
import org.cigma.ecom.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PanierServiceImp implements IPanierService{
    @Autowired
    PanierRepository repository;

    @Override
    public Panier insertPanier(Panier p, String username) {
        if (username.equals(p.getProprietaire().getUsername())){
            return repository.save(p);
        }
        return null;
    }

    @Override
    public Panier updatePanier(Panier p, String username) {
        if (username.equals(p.getProprietaire().getUsername())){
            Panier old = repository.findById(p.getId()).get();
            old.setQuantite(p.getQuantite());
            old.setDate(p.getDate());
            old.setProprietaire(p.getProprietaire());
            old.setArticle(p.getArticle());
            return repository.save(
                    old
            );
        }
        return null;
    }

    @Override
    public void deletePanier(int id, String username) {
        Panier p = repository.findById(id).get();
        if (username.equals(p.getProprietaire().getUsername())){
            repository.deleteById(id);
        }
    }

    @Override
    public Panier selectOne(int id, String username) {
        Panier p = repository.findById(id).get();
        if (username.equals(p.getProprietaire().getUsername())){
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Panier> selectAll(String username) {
        return (List<Panier>) repository.findPanierByProprietaireUsername(username);
    }

    @Override
    public Page<Panier> getPage(Pageable p, String username) {
        return (Page<Panier>) repository.findPanierByProprietaire_Username(p,username);
    }
}
