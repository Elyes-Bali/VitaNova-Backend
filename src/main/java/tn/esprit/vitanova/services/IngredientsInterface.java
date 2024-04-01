package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.IngredientDto;
import tn.esprit.vitanova.entities.Ingredients;
import tn.esprit.vitanova.entities.Products;

import java.util.List;

public interface IngredientsInterface {
    Ingredients addIngredient(Ingredients ingredient);
    Ingredients getIngredientById(Long id);
    List<Ingredients> getAllIngredients();
    public void updateIngredients( Ingredients p);
    public void deleteIngredients( Long id);
    //List<Ingredients> getIngredientsByRecipeId(Long recipeId);
    List<Ingredients> getAllIngredientsWithRecipes();
}