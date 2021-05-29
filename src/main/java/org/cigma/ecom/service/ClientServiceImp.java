package org.cigma.ecom.service;

import org.cigma.ecom.model.Client;
import org.cigma.ecom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientServiceImp implements IClientService{
    @Autowired
    ClientRepository repository;

    @Override
    public Client insertClient(Client c) {
        if (repository.existsById(c.getId()) || repository.existsUsername(c.getUsername()) || repository.existsEmail(c.getEmail())){
            return null;
        }
        return repository.save(c);
    }

    @Override
    public Client updateClient(Client c, String username) {
        Client old = repository.findById(c.getId()).get();
        old.setVille(c.getVille());
        old.setTele(c.getTele());
        old.setPrenom(c.getPrenom());
        old.setNom(old.getNom());
        old.setEmail(old.getEmail());
        return repository.save(c);
    }

    @Override
    public void deleteClient(int id, String username) {
        Client c = repository.findById(id).get();
        if (c.getUsername() == username)
            repository.deleteById(id);
    }

    @Override
    public Client selectClientById(int id) {
        return null;
    }

    @Override
    public List<Client> selectAll() {
        return null;
    }
}
