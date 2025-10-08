package org.esprim.foyer.service;

import org.esprim.foyer.entity.Bloc;
import org.esprim.foyer.repository.BlocRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BlocServiceImpl implements BlocServiceI {
    BlocRepository blocRepository;

    @Override
    public List<Bloc> retrieveAllBoc() {
        return blocRepository.findAll();

    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepository.findById(idBloc).get();

    }

    @Override
     public void deleteBloc(long idBloc){
        blocRepository.deleteById(idBloc);
    }
    @Override
    public Bloc modifyBloc(Bloc bloc){
        return blocRepository.save(bloc);
    }
    @Override
    public Bloc addBloc(Bloc bloc){
        return blocRepository.save(bloc);
    }
}