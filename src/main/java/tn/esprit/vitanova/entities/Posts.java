package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Posts")
public class Posts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPosts")
    private Long idPosts; // Clé primaire
    private String Post;
    private String ImageP;
    private String Description;
    private Long idOwner;

    @ManyToOne
    @JoinColumn(name = "community_id_community")
    @JsonIgnore
    private Community community;

    @OneToMany(mappedBy = "posts", orphanRemoval = true)
    @JsonIgnore
    private Set<Comments> comments = new LinkedHashSet<>();

}
