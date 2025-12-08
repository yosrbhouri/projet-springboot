package org.esprim.foyer.service;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Foyer;
import org.esprim.foyer.entity.Universite;
import org.esprim.foyer.repository.FoyerRepository;
import org.esprim.foyer.repository.UniversiteRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements UniversiteServiceI{

private  UniversiteRepository universiteRepository;
private FoyerRepository foyerRepository;

@Override
public  Universite affecterUniversite(Long foyerId, String nomuniversite) {
    Foyer foyer= foyerRepository.findById(foyerId).orElseThrow(() ->new RuntimeException("Foyer introvable avec l'ID:"+foyerId));
    Universite universite=  universiteRepository.findByNomuniversite(nomuniversite).orElseThrow(() ->new RuntimeException("universite intovable avec le nom universite:"+nomuniversite));
    if(foyer.getUniversite() !=null ||
    universite.getFoyer() !=null)
    {
        throw new RuntimeException("l'associaction deja pour ce foyer"+foyerId+
        "ou cette universite");

    }
    universite.setFoyer(foyer);
    foyer.setUniversite(universite);
    universiteRepository.save(universite);

return universite;
}
    @Override
    public  Universite desaffecterUniversite(Long iduniversite) {

        Universite universite=  universiteRepository.findById(iduniversite).orElse(null);
        if(universite.getFoyer() == null){
            throw new RuntimeException("aucun foyer introuvable associe  a cette universite");
        }

      Foyer foyer= universite.getFoyer();
        universite.setFoyer(null);
        foyer.setUniversite(null);
        universiteRepository.save(universite);
      foyerRepository.save(foyer);
       return universite;



    }
@Override
    public List<Universite> getAllUniversiter() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(long id) {
        return  universiteRepository.findById(id).get();
    }

    @Override
    public void deleteUniversiteById(long iduniversite) {
        universiteRepository.deleteById(iduniversite);

    }



    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite modifyUniversity(Universite universite) {
        return null;
    }


}
