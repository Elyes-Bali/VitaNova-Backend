package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Recepies;
import tn.esprit.vitanova.entities.RecipeDto;

import java.util.List;

public interface IRecepiesService {
    Recepies addRecipe(RecipeDto recipe);
    Recepies getRecipeById(Long id);
    List<Recepies> getAllRecipes();
    void updateRecipe(Recepies recipe);
    void deleteRecipe(Long id);
    List<Recepies> getAllRecipesWithIngredients(Long ingredientId);
}
