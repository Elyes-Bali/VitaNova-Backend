package tn.esprit.vitanova.entities;

import lombok.Data;


@Data
public class PsychologistRecommendationRequest {
    private String genderPreference;
    private boolean specializeDepression;
    private boolean specializeRelationship;
    private boolean specializeAnxiety;
}
