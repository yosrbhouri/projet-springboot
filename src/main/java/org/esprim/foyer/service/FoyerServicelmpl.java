package org.esprim.foyer.service;




import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Bloc;
import org.esprim.foyer.entity.Foyer;
import org.esprim.foyer.entity.Universite;
import org.esprim.foyer.repository.FoyerRepository;
import org.esprim.foyer.repository.UniversiteRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
class FoyerServiceImpl implements FoyerServiceI {

    FoyerRepository foyerRepository;
    UniversiteRepository universiteRepository;

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer ,Long iduniversite) {
        Universite universite = universiteRepository.findById(iduniversite).orElseThrow(() -> new RuntimeException("universite introvable avec l'ID:" + iduniversite));

        if (universite.getFoyer() != null)
            throw new RuntimeException("l'associaction deja affecte a foyer");
        for (Bloc bloc : foyer.getBlocs()) {
            bloc.setFoyer(foyer);
            //if(foyer.getBlocs() !=null)
            //foyer.getBlocs().forEach(Bloc bloc -> bloc.setFoyer(foyer));
            Foyer savedFoyer = foyerRepository.save(foyer);
            universite.setFoyer(savedFoyer);
            universiteRepository.save(universite);
            return savedFoyer;


        }
        return foyer;



    }

    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId).get();
    }

    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }
}

