package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RapportPsy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRapportPsy")
    private Long idRapportPsy; // Clé primaire

    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateRappPs;


    @JsonIgnoreProperties({"chats", "rapportPsy", "notifications", "clients"})
    @ManyToOne
    Psychologue psychologue;
    @JsonIgnoreProperties({"psychologue","rapportPsy"})
    @OneToOne
    Client client;
}
