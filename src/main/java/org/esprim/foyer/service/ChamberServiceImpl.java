package org.esprim.foyer.service;
import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.entity.TypeChambre;
import org.esprim.foyer.repository.ChambreRepsitory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ChamberServiceImpl implements ChamberServiceI {
    ChambreRepsitory chambreRepsitory;
    @Override
    public List<Chambre> retrieveAllChambre() {
        return chambreRepsitory.findAll();
    }

    @Override
    public Chambre retrieveChambre(long chambreId ){
        return chambreRepsitory.findById(chambreId).get();
    }

    @Override
    public void removeChambre(long chambreId) {
         chambreRepsitory.deleteById(chambreId);
    }


    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepsitory.save(chambre);
    }

    public Chambre addChambre(Chambre c) {
        return chambreRepsitory.save(c);
    }
    @Override
    public Chambre retrieveTypeC(String typeChambre) {
        return chambreRepsitory.findByTypeC(TypeChambre.valueOf(typeChambre.toUpperCase()));
    }
}
