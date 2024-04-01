package tn.esprit.vitanova.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private Long id;
    private String description;
    private String images;
    private Set<IngredientDto> ingredients;
    public RecipeDto(Long idRecepies, String description, String images) {
        this.id = idRecepies;
        this.description = description;
        this.images = images;
    }
}
