package org.cigma.ecom.service;

import org.cigma.ecom.model.Panier;
import org.cigma.ecom.repository.ClientRepository;
import org.cigma.ecom.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PanierServiceImp implements IPanierService {
    @Autowired
    PanierRepository repository;
    @Autowired
    ClientRepository client;

    @Override
    public Panier insertPanier(Panier p, String username) {
        if (p.getQuantite() > 0) {
            p.setProprietaire(client.findClientByUsername(username));
            return repository.save(p);
        }
        return null;
    }

    @Override
    public Panier updatePanier(Panier p, String username) {
        if (username.equals(p.getProprietaire().getUsername())){
            Panier old = repository.findById(p.getId()).get();
            if (p.getQuantite() > 0)
                old.setQuantite(p.getQuantite());
            else old.setQuantite(1);
            old.setDate(p.getDate());
            //old.setProprietaire(p.getProprietaire());
            old.setArticle(p.getArticle());
            return repository.save(old);
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
        return repository.findPanierByProprietaire_Username(username);
    }

    @Override
    public Page<Panier> getPage(Pageable p, String username) {
        return repository.findPanierByProprietaire_Username(p,username);
    }
}
