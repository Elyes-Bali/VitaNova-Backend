package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String nom;

    private String prenom;

    private String gender;
    private String desctiption;
    private String achievments;
    private String fears;
    private int age;
    private Boolean banned;
    private Boolean activated;

    private String activationToken;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime activationTokenCreationDate;
    private String pwdToken;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime pwdTokenCreationDate;

    @ManyToOne
    @JoinColumn(name = "community_id_community")
    @JsonIgnore
    private Community community;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserAchievementHistory> userAchievementHistories = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "User_achievements",
            joinColumns = @JoinColumn(name = "user_idUser"),
            inverseJoinColumns = @JoinColumn(name = "achievements_id"))
    private Set<Achievement> achievements = new LinkedHashSet<>();

    private boolean mfaEnabled;
    private String secret;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
