package org.cigma.ecom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Admin extends Compt {
    private String nom;
    private String tele;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Article> listArticles;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public void setListArticles(List<Article> listArticles) {
        this.listArticles = listArticles;
    }

    public String getNom() {
        return nom;
    }

    public String getTele() {
        return tele;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }
}
