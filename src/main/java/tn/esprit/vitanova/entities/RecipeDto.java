package tn.esprit.vitanova.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private Long id;
    private Date datePreparation;
    private Date dateAdded;
    @Column(length = 1000) // Définir une taille de colonne de 1000 caractères pour la description
    private String description;


    private String images;

    @Column(length = 1000) // Définir une taille de colonne de 1000 caractères pour la description
    private String name;
    private Set<IngredientDto> ingredients;
    public RecipeDto(Long idRecepies, String description, String images) {
        this.id = idRecepies;
        this.description = description;
        this.images = images;
    }
}
