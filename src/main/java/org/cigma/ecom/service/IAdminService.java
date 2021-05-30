package org.cigma.ecom.service;

import org.cigma.ecom.model.Administrateur;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    Administrateur insertAdmin(Administrateur a);
    Administrateur updateAdmin(Administrateur a,String username);
    void deleteAdmin(int id, String username);
    Optional<Administrateur> selectClientById(int id);
    List<Administrateur> selectAll();
}
