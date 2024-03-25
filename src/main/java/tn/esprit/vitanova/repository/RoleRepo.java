package tn.esprit.vitanova.repository;

import java.util.Optional;

import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
