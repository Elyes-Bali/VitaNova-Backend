package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Consultation implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation ;
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Temporal(TemporalType.DATE)
    private Date consultationdate;
    @ManyToOne
    Psychologue psychologue;
    @ManyToOne
    Client client;


}
