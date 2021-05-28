package org.cigma.ecom.service;

import org.cigma.ecom.model.Client;

import java.util.List;

public interface IClientService {
    Client insertClient(Client c);
    Client updateClient(Client c);
    void deleteClient(int id);
    Client selectClientById(int id);
    List<Client> selectAll();
}
