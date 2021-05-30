package org.cigma.ecom.repository;

import org.cigma.ecom.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,Integer>{
    @Query("select case when count(c)> 0 then true else false end from Client c where lower(c.username) like lower(:username)")
    public boolean existsUsername(@Param("username") String username);

    @Query("select case when count(c)> 0 then true else false end from Client c where lower(c.email) like lower(:email)")
    public boolean existsEmail(@Param("email") String email);

    public Client findClientByUsername(String username);
}
