package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Client implements Serializable
{ @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long clientId;
    private String nom;
    private String prenom;
    private String email;
    private String phonenumber;

    @ManyToOne
    Psychologue psychologue;

    @OneToOne (mappedBy = "client")
    private RapportPsy rapportPsy;
    @OneToMany(cascade =CascadeType.ALL,mappedBy = "client")
     private List<Consultation>consultations;
}
