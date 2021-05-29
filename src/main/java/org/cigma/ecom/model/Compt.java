package org.cigma.ecom.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Compt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    @Column(unique=true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique=true)
    private String email;
    private String role = "USER";

    public void setUsername(String username) {
        if(this.password == null){
            this.username = username;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String newPassword, String oldPassword) {
        if (this.password == oldPassword){
            this.password = newPassword;
        }
    }

    public boolean signin(String username, String password) {
        if (this.username == username || this.email == username){
            if (this.password == password){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public void setRole(String role) {
        this.role = role;
    }
}
