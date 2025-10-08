package org.esprim.foyer.service;

import org.esprim.foyer.entity.Chambre;
import java.util.List;

public interface ChamberServiceI {
    public List<Chambre> retrieveAllChambre();
    public Chambre retrieveChambre(long id);
    public Chambre retrieveTypeChambre(String typeChambre);
    public void removeChambre(long chambreId);
    public Chambre modifyChambre (Chambre chambre);
    public Chambre  addChambre (Chambre chambre);


}
