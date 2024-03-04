package tn.esprit.vitanova.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.RapportPsy;
@Repository
public interface RapportPsyRepo extends JpaRepository<RapportPsy,Long> {
}
