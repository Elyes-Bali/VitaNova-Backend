package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Consultation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
    @Query("SELECT c FROM Consultation c WHERE c.consultationdate = :date AND c.psychologue.id = :psychologueId")
    List<Consultation> findByConsultationdateAndPsychologueId(@Param("date") LocalDate date, @Param("psychologueId") Long psychologueId);
    @Query("SELECT c.startTime FROM Consultation c WHERE c.consultationdate = :date AND c.psychologue.psychologueId= :psychologueId")
    List<LocalTime> findStartTimesByDateAndPsychologueId(@Param("date") LocalDate date, @Param("psychologueId") Long psychologueId);
}

