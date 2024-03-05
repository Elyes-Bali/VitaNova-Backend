package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Consultation;
@Repository
public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
}
