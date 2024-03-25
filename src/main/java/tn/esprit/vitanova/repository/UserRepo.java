package tn.esprit.vitanova.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    long countByBannedTrue();

    long count();

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByPwdToken(String token);

    Optional<User> findByActivationToken(String token);


    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}

