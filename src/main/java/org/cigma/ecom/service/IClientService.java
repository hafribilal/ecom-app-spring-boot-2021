package org.cigma.ecom.service;

import org.cigma.ecom.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client insertClient(Client c);
    Client updateClient(Client c,String username);
    void deleteClient(int id, String username);
    Optional<Client> selectClientById(int id);
    List<Client> selectAll();
}
