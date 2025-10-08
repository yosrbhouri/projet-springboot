package org.esprim.foyer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBloc;

    String nomBloc;
    long capaciteBloc;

    @ManyToOne
    Foyer foyer;


    @OneToMany(mappedBy = "bloc",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    Set<Chambre> chambres = new HashSet<Chambre>();


}