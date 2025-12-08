package org.esprim.foyer.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEtudiant;

    String nomEtudiant;
    String prenomEtudiant;
    Long cinEtudiant;
    Date dateNaissance;

    @ManyToMany(mappedBy = "etudiants", cascade = CascadeType.ALL)
    Set<Reservation> reservations;

    public <E> Etudiant(String jane, String smith, int i, Date date, HashSet<E> es) {
    }
}