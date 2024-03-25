package tn.esprit.vitanova.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Long idUser; // Clé primaire
    private String nomUser;
    private String email;
    private String password;
    private String gender;
    private String prenomUser;
    private String username;
    private String desctiption;
    private String achievments;
    private String fears;
    private int age;
    private String role;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @OneToMany(cascade = CascadeType.ALL)
   private Set<Products> products;

}
