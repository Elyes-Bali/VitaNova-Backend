package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table( name = "Products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProducts")
    private Long idProducts; // Clé primaire
    private String prodName;
    @Enumerated(EnumType.STRING)
    private ProductType typeProd;
    private Long price;
    private int quantityP;
    private String descriptionP;
    private String imageUrl;
//    @Temporal(TemporalType.DATE)
    private String expiration;
    @ManyToOne
    Cart cart;
}
