package org.esprim.foyer.service;

import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.entity.TypeChambre;

import java.util.List;

public interface ChamberServiceI {
    public List<Chambre> retrieveAllChambre();
    public Chambre retrieveChambre(Long id);
    public Chambre retrieveTypeC(String typeChambre);
    public void removeChambre(long chambreId);
    public Chambre modifyChambre (Chambre chambre);
    public Chambre  addChambre (Chambre chambre);
    public  List<Chambre> getChambreParNomUniversite(String nomUniversite);
    public List<Chambre> getChambresParBlocEtType (Long idBloc, TypeChambre
            typeC) ;
public void pourcentageChalbreParTypeChambre();
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(
            String nomUniversite, TypeChambre type);

}
