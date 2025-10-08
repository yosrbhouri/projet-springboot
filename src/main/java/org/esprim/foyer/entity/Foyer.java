package org.esprim.foyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFoyer;
    String nomFoyer;
    long capaciteFoyer;
    @OneToOne(mappedBy = "foyer")
    @ToString.Exclude
    @JsonIgnore
    Universite universite;
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    Set<Bloc> blocs;

}
