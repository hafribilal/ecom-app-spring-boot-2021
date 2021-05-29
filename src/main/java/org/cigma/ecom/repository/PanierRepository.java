package org.cigma.ecom.repository;

import org.cigma.ecom.model.Panier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PanierRepository extends PagingAndSortingRepository<Panier,Integer> {
    List<Panier> findPanierByProprietaireUsername(String username);
    Page<Panier> findPanierByProprietaire_Username(Pageable p, String username);
}
