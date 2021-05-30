package org.cigma.ecom.service;

import org.cigma.ecom.model.Administrateur;
import org.cigma.ecom.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImp implements IAdminService{
    @Autowired
    AdministrateurRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Administrateur insertAdmin(Administrateur a) {
        if (repository.existsById(a.getId()) || repository.existsUsername(a.getUsername()) || repository.existsEmail(a.getEmail())){
            return null;
        }
        a.setPassword(passwordEncoder.encode(a.getPassword()),a.getPassword());
        a.setRole("ADMIN");
        return repository.save(a);
    }

    @Override
    public Administrateur updateAdmin(Administrateur a, String username) {
        if (username.equals(a.getUsername())){
            Administrateur old = repository.findById(a.getId()).get();
            old.setTele(a.getTele());
            old.setNom(old.getNom());
            if (!repository.existsEmail(a.getEmail()))
                old.setEmail(a.getEmail());
            return repository.save(old);
        }
        return null;
    }

    @Override
    public void deleteAdmin(int id, String username) {
        Administrateur a = repository.findById(id).get();
        if (a.getUsername() == username)
            repository.deleteById(id);
    }

    @Override
    public Optional<Administrateur> selectClientById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Administrateur> selectAll() {
        return (List<Administrateur>) repository.findAll();
    }
}
