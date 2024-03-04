package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data

public class Notifications implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotifications; // Clé primaire
    private Long DestinatorId;
    private String ContentNot;
    @ManyToMany (cascade = CascadeType.ALL)
    private Set<Psychologue>psychologues;
}
