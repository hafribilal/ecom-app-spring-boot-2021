package org.cigma.ecom.model;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private String type;
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Administrateur vendeur;

}
