package org.cigma.ecom.model;

import lombok.Getter;
import lombok.Setter;
import org.cigma.ecom.model.Panier;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends Compt implements Serializable {
    private String nom;
    private String prenom;
    private String ville;
    private String tele;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Panier> paniers;

}
