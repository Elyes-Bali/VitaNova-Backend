package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Recepies;

import java.util.List;

@Repository
public interface RecepiesRepository  extends JpaRepository<Recepies,Long> {
    @Query("SELECT DISTINCT r FROM Recepies r JOIN r.ingredients i WHERE i.idIngredients = :ingredientId")
    List<Recepies> findAllRecipesByIngredientId(Long ingredientId);
}
