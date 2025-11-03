package org.esprim.foyer.service;

import org.esprim.foyer.entity.Universite;
import org.esprim.foyer.repository.UniversiteRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UniversiteServiceImpl implements UniversiteServiceI{






    UniversiteRepository universiteRepository;

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
