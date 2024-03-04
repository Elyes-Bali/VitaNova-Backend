package tn.esprit.vitanova.entities;

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
@Table( name = "PremuimV")
public class PremuimV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPremuimV")
    private Long idPremuimV; // Clé primaire
    private Long OwnerId;
    @Temporal(TemporalType.DATE)
    private Date ExpirationDate;
    @Temporal(TemporalType.DATE)
    private Date StartingDate;
    private Long Price;
}
