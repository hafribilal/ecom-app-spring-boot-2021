package org.cigma.ecom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private int prix = 0;
    private String type;
    private int stock = 0;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Admin vendeur;
//    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    private List<Galerie> galerieList;

}
