package org.cigma.ecom.model;

import lombok.Getter;
import lombok.Setter;
import org.cigma.ecom.model.Panier;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Client extends Compt{
    private String nom;
    private String prenom;
    private String ville;
    private String tele;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Panier> paniers;

}
