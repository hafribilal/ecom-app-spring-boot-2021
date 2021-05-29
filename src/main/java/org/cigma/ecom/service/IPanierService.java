package org.cigma.ecom.service;

import org.cigma.ecom.model.Panier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPanierService {
    Panier insertPanier(Panier p, String username);
    Panier updatePanier(Panier p, String username);
    void deletePanier(int id, String username);
    Panier selectOne(int id, String username);
    List<Panier> selectAll(String username);
    public Page<Panier> getPage(Pageable p, String username);
}
