package tn.esprit.vitanova.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Recepies;
import tn.esprit.vitanova.entities.RecipeDto;
import tn.esprit.vitanova.services.IRecepiesService;
import tn.esprit.vitanova.services.RecepiesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RecipesController {
    @Autowired
    private IRecepiesService recipesService;

    // Endpoint pour ajouter une recette
    @PostMapping
    public ResponseEntity<Recepies> addRecipe(@RequestBody RecipeDto recipeDto) {
        Recepies createdRecipe = recipesService.addRecipe(recipeDto);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    // Endpoint pour obtenir une recette par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Recepies> getRecipeById(@PathVariable Long id) {
        Recepies recipe = recipesService.getRecipeById(id);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/ingredients/{ingredientId}")
    public ResponseEntity<List<Recepies>> getAllRecipesWithIngredients(@PathVariable Long ingredientId) {
        List<Recepies> recipes = recipesService.getAllRecipesWithIngredients(ingredientId);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    // Endpoint pour obtenir toutes les recettes
    @GetMapping
    public ResponseEntity<List<Recepies>> getAllRecipes() {
        List<Recepies> recipes = recipesService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    // Endpoint pour mettre à jour une recette
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRecipe(@PathVariable Long id, @RequestBody Recepies recipe) {
        recipe.setIdRecepies(id); // Assurez-vous que l'ID de la recette est correct
        recipesService.updateRecipe(recipe);
        System.out.println(recipe.toString());
         Map<String, String> response = new HashMap<>();
        response.put("message", "Recette mise à jour avec succès.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint pour supprimer une recette
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRecipe(@PathVariable Long id) {
        recipesService.deleteRecipe(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Recette supprimée avec succès.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
