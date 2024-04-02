package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    private String content;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "chatRoom_id", nullable = false)
    private ChatRoom chatRoom;
}
