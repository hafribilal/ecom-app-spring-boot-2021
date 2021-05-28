package org.cigma.ecom.service;

import org.cigma.ecom.model.Client;

import java.util.List;

public interface IClientService {
    Client insertClient (Client c);
    Client updateClient (Client c);
    void deleteClient (int id);
    public Client selectClientById (int id);
    public List<Client> selectAll();
}
