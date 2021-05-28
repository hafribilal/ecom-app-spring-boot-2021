package org.cigma.ecom.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Administrateur extends Compt{
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
