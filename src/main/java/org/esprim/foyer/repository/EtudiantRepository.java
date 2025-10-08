package org.esprim.foyer.repository;

import org.esprim.foyer.entity.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import org.esprim.foyer.entity.Etudiant;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

}
