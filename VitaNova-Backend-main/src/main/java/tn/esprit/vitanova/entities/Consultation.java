package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Consultation implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation ;
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;
    @Temporal(TemporalType.DATE)
    private LocalDate consultationdate;
    @JsonIgnoreProperties({"chats", "notifications", "clients","rapportPsy","consultations"})
    @ManyToOne
    Psychologue psychologue;
    @JsonIgnoreProperties({"psychologue","rapportPsy","consultations"})
    @ManyToOne
    Client client;


}
