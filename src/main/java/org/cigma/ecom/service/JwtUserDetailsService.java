package org.cigma.ecom.service;

import org.cigma.ecom.model.Compt;
import org.cigma.ecom.repository.AdministrateurRepository;
import org.cigma.ecom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private ClientRepository cRepo;
    @Autowired
    private AdministrateurRepository aRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //ADMIN ROLE
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList( "ROLE_ADMIN");
        //USER ROLE
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        //GUEST ROLE
        List<GrantedAuthority> authorityListGuest = AuthorityUtils.createAuthorityList("ROLE_GUEST");
        //Account
        Compt compt;
        //Authority
        List<GrantedAuthority> comptAuthority;

        if (aRepo.existsUsername(username)){
            System.out.println("Compt is ADMIN");
            compt = aRepo.findAdministrateurByUsername(username);
            System.out.println("Compt is initialized");
            comptAuthority = authorityListAdmin;
        }else if (cRepo.existsUsername(username)){
            System.out.println("Compt is USER");
            compt = cRepo.findClientByUsername(username);
            System.out.println("Compt is initialized");
            comptAuthority = authorityListUser;
        }else {
            System.out.println("Compt NOT-FOUND");
            compt = new Compt();
            System.out.println("Compt is initialized");
            comptAuthority = authorityListGuest;
        }

        System.out.println("Compt Username "+compt.getUsername()+" Role: "+compt.getRole());
        //compt.getRole()=="ADMIN" ? authorityListAdmin : authorityListUser
        User user = new User(compt.getUsername(), compt.getPassword(), comptAuthority);
        System.out.println("User  Username "+user.getUsername()+" Role: "+user.getAuthorities());
        return user;

//        if ("cigma".equals(username)) {
//
//            return new User("cigma", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }

}
