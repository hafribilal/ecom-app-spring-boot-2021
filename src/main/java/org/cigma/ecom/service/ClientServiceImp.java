package org.cigma.ecom.service;

import org.cigma.ecom.model.Client;
import org.cigma.ecom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImp implements IClientService{
    @Autowired
    ClientRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Client insertClient(Client c) {
        if (repository.existsById(c.getId()) || repository.existsUsername(c.getUsername()) || repository.existsEmail(c.getEmail())){
            System.out.println("Signup refused !!!");
            System.out.println("ID = " + c.getId() + " ~ Username = " + c.getUsername() + " ~ Email = " + c.getEmail());
            return null;
        }
        c.setPassword(passwordEncoder.encode(c.getPassword()),c.getPassword());
        c.setRole("USER");
        return repository.save(c);
    }

    @Override
    public Client updateClient(Client c, String username) {
        if (username.equals(c.getUsername())){
            Client old = repository.findById(c.getId()).get();
            old.setVille(c.getVille());
            old.setTele(c.getTele());
            old.setPrenom(c.getPrenom());
            old.setNom(old.getNom());
            if (!repository.existsEmail(c.getEmail()))
                old.setEmail(c.getEmail());
            return repository.save(old);
        }
        return null;
    }

    @Override
    public void deleteClient(int id, String username) {
        Client c = repository.findById(id).get();
        if (c.getUsername() == username)
            repository.deleteById(id);
    }

    @Override
    public Optional<Client> selectClientById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Client> selectAll() {
        return (List<Client>) repository.findAll();
    }
}
