package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Answers;

public interface AnswersRepo extends JpaRepository<Answers,Long> {
}
