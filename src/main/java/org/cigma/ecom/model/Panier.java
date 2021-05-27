package org.cigma.ecom.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantite;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client proprietaire;
    @OneToOne
    private Article article;

}
