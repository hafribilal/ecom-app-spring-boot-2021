package org.cigma.ecom.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Administrateur extends Compt{
    private String nom;
    private String tele;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
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
