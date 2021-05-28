package org.cigma.ecom.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Compt {
    @Id
    private int Id;
    private String username;
    private String password;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String newPassword, String oldPassword) {
        if (this.password == oldPassword){
            this.password = newPassword;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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

    public boolean signup(String username, String password, String email){
        if (this.username == null && this.email == null && this.password == null){
            return false;
        }else {
            this.username = username;
            this.password = password;
            if (email.contains("@")){
                this.email = email;
            }
            return true;
        }
    }
}
