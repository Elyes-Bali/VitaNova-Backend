package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idquestion;

    private String text;
    @JsonIgnore
    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    private Answers answer;

}
