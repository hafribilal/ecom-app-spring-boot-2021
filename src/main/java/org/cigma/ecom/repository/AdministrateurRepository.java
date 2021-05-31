package org.cigma.ecom.repository;

import org.cigma.ecom.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdministrateurRepository extends CrudRepository<Admin, Integer> {
    @Query("select case when count(a)> 0 then true else false end from Admin a where lower(a.username) like lower(:username)")
    boolean existsUsername(@Param("username") String username);

    @Query("select case when count(a)> 0 then true else false end from Admin a where lower(a.email) like lower(:email)")
    boolean existsEmail(@Param("email") String email);

    Admin findAdministrateurByUsername(String username);
}
