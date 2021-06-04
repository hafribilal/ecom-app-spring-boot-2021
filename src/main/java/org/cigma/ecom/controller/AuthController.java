package org.cigma.ecom.controller;

import org.cigma.ecom.model.Admin;
import org.cigma.ecom.model.Client;
import org.cigma.ecom.model.Compt;
import org.cigma.ecom.service.IAdminService;
import org.cigma.ecom.service.IClientService;
import org.cigma.ecom.service.JwtUserDetailsService;
import org.cigma.ecom.service.SmtpService;
import org.cigma.ecom.util.CheckUser;
import org.cigma.ecom.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    IClientService cService;
    @Autowired
    IAdminService aService;
    @Autowired
    SmtpService smtpService;
    @Autowired
    CheckUser checkUser;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createAuthenticationToken(@RequestBody Compt authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Token : " + token);
        return token;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Authenticate Username : "+username);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/client/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> clientSignUp(@RequestBody Client client) throws Exception {
        HttpStatus status;
        client = cService.insertClient(client);
        if (client == null)
            status = HttpStatus.NOT_ACCEPTABLE;
        else {
            status = HttpStatus.OK;
            //send Mail
            smtpService.SendMail(client);
            //Just for security the password don't return to client
            client.setPassword("xxxxxx", client.getPassword());
        }
        return new ResponseEntity<>(client,status);

    }

    @PostMapping(value = "/admin/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adminSignUp(@RequestBody Admin admin) throws Exception {
        HttpStatus status;
        admin = aService.insertAdmin(admin);
        if (admin == null)
            status = HttpStatus.NOT_ACCEPTABLE;
        else {
            status = HttpStatus.OK;
            //send Mail
            smtpService.SendMail(admin);
            //Just for security the password don't return to client
            admin.setPassword("xxxxxx", admin.getPassword());
        }
        return new ResponseEntity<>(admin, status);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUser(@RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return userDetailsService.loadUserByUsername(username).getAuthorities().toString();
    }
}
