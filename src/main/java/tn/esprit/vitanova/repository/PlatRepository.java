package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Plat;

import java.util.Optional;

public interface PlatRepository extends JpaRepository<Plat,Long> {
}
