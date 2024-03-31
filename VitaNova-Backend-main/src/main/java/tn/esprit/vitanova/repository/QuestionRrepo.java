package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Question;

public interface QuestionRrepo extends JpaRepository<Question,Long> {
}
