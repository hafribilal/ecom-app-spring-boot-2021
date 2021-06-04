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
            List<Panier> myPaniers = repository.findPanierByProprietaire_Username(username);
            for (int i = 0; i < myPaniers.toArray().length; i++) {
                Panier selected = myPaniers.get(i);
                if (selected.getArticle().getId() == p.getArticle().getId()) {
                    selected.setQuantite(selected.getQuantite() + p.getQuantite());
                    p = selected;
                }
            }
            p.setProprietaire(client.findClientByUsername(username));
            p = repository.save(p);
            //p.getProprietaire().hidePassword();
            return p;
        }
        return null;
    }

    @Override
    public Panier updatePanier(Panier p, String username) {
        if (username.equals(p.getProprietaire().getUsername())) {
            Panier old = repository.findById(p.getId()).get();
            if (p.getQuantite() > 0)
                old.setQuantite(p.getQuantite());
            else old.setQuantite(1);
            old.setDate(p.getDate());
            //old.setProprietaire(p.getProprietaire());
            old.setArticle(p.getArticle());
            old = repository.save(old);
            //old.getProprietaire().hidePassword();
            return old;
        }
        return null;
    }

    @Override
    public void deletePanier(int id, String username) {
        Panier p = repository.findById(id).get();
        System.out.println("Delete " + id);
        if (username.equals(p.getProprietaire().getUsername())){

            repository.deleteById(id);
        }
    }

    @Override
    public Panier selectOne(int id, String username) {
        Panier p = repository.findById(id).get();
        if (username.equals(p.getProprietaire().getUsername())) {
            p = repository.findById(id).get();
            //p.getProprietaire().hidePassword();
            return p;
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
