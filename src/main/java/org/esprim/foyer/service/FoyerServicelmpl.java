package org.esprim.foyer.service;




import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Foyer;
import org.esprim.foyer.repository.FoyerRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
class FoyerServiceImpl implements FoyerServiceI {

    FoyerRepository foyerRepository;

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

