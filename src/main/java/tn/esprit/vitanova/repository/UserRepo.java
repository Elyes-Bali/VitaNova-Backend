package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.User;

public interface UserRepo extends JpaRepository<User,Long> {
}
