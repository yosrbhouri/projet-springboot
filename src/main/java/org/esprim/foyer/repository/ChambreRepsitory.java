package org.esprim.foyer.repository;
 import org.esprim.foyer.entity.Chambre;
 import org.esprim.foyer.entity.TypeChambre;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
 @Repository
public interface ChambreRepsitory extends JpaRepository<Chambre,Long> {
     Chambre findByTypeC( TypeChambre typeC);
}
