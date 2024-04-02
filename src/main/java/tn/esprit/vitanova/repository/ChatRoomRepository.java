package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.ChatRoom;

import java.util.List;
import java.util.Optional;
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByClientId(Long clientId);
    List<ChatRoom> findByNutritionistId(Long nutritionistId);
}
