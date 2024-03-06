package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class Consultation implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation ;

    private LocalTime startTime;
    @Temporal(TemporalType.DATE)
    private LocalDate consultationdate;
    @ManyToOne
    Psychologue psychologue;
    @ManyToOne
    Client client;


}
