package org.esprim.foyer.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chambre  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idChambre;

    Long numeroChambre;

    @Enumerated(EnumType.STRING)
    TypeChambre typeC;




    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "chambre")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    Bloc bloc;


}