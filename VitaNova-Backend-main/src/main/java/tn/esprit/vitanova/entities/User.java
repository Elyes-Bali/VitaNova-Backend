package tn.esprit.vitanova.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(	name = "users",
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
        private Double Rating;

        @Column(columnDefinition = "TIMESTAMP")
        private LocalDateTime activationTokenCreationDate;
        private String pwdToken;

        @Column(columnDefinition = "TIMESTAMP")
        private LocalDateTime pwdTokenCreationDate;

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


    @JsonIgnore
    @OneToMany(mappedBy = "psychiatrist", cascade = CascadeType.ALL)
    private List<Consultation> consultationsAsPsychiatrist;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Consultation> consultationsAsClient;
    @OneToMany(mappedBy = "psychiatrist", cascade = CascadeType.ALL)
    private List<RapportPsy> psychiatristReports;

    // Association with client report (one-to-one)
    @OneToOne(mappedBy = "clients")
    private RapportPsy clientReport;

    }

