package org.cigma.ecom.repository;

import org.cigma.ecom.model.Galerie;
import org.springframework.data.repository.CrudRepository;

public interface GalerieRepository extends CrudRepository<Galerie, Long> {
    Galerie findGalerieByArticleId(int id);
}
