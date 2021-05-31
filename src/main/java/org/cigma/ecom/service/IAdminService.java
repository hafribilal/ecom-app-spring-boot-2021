package org.cigma.ecom.service;

import org.cigma.ecom.model.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    Admin insertAdmin(Admin a);

    Admin updateAdmin(Admin a, String username);

    void deleteAdmin(int id, String username);

    Optional<Admin> selectClientById(int id);

    List<Admin> selectAll();
}
