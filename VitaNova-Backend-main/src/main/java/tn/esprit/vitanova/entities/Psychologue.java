package tn.esprit.vitanova.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Psychologue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long psychologueId;
    private String nom;
    private String prenom;
    private String email;
    private String phonenumber;
    @JsonIgnore
    @OneToMany
            (cascade = CascadeType.ALL , mappedBy= "psychologue")
    private Set<Chat> chats;


    @OneToMany( mappedBy= "psychologue",cascade = CascadeType.ALL)
    private List<RapportPsy> rapportPsy;
    @JsonIgnore
    @ManyToMany (mappedBy = "psychologues",cascade = CascadeType.ALL)
    private List<Notifications>notifications;
    @JsonIgnoreProperties({"psychologue", "rapportPsy"})
    @OneToMany
            (cascade = CascadeType.ALL , mappedBy= "psychologue")
    private List<Client> clients;
    @OneToMany (cascade =CascadeType.ALL,mappedBy = "psychologue")
    private List<Consultation> consultations;
}
