package org.esprim.foyer.repository;

import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.entity.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepsitory extends JpaRepository<Chambre, Long> {

    Chambre findByTypeC(TypeChambre typeC);

    List<Chambre> findAllByNumeroChambreIn(List<Long> numChambres);

    @Query("SELECT c FROM Chambre c " +
            "WHERE c.bloc.foyer.universite.nomuniversite = :nomUniversite")
    List<Chambre> getChambresParNomUniversite(@Param("nomUniversite") String nomUniversite);

    @Query("SELECT c FROM Chambre c " +
            "WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeChambre")
    List<Chambre> getChambresParBlocEtType(@Param("idBloc") Long idBloc,
                                           @Param("typeChambre") TypeChambre typeChambre);
    @Query("""
       SELECT c FROM Chambre c
       JOIN c.bloc b
       JOIN b.foyer f
       WHERE f.universite.nomuniversite = :nomUniversite
       AND c.typeC = :type
       AND c NOT IN (
            SELECT r.chambre FROM Reservation r
            WHERE r.anneeUniversitaire = :annee
       )
       """)
    List<Chambre> getChambresNonReserveParNomUniversiteEtType(
            @Param("nomUniversite") String nomUniversite,
            @Param("type") TypeChambre type,
            @Param("annee") Integer anneeUniversitaire);


}
