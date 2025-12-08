package org.esprim.foyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.esprim.foyer.entity.Bloc;
import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.repository.BlocRepository;
import org.esprim.foyer.repository.ChambreRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class BlocServiceImpl implements BlocServiceI {
    BlocRepository blocRepository;
    ChambreRepsitory chambreRepsitory;
    @Override
@Scheduled(cron = "0/15 * * * * *")
public void  listChambreBlocs(){
    List<Bloc> blocs = blocRepository.findAll();
    if(!blocs.isEmpty())
        for(Bloc bloc : blocs){
            log.info("listChambreBlocs: "+bloc.getNomBloc()+"ayant une capacite" + bloc.getCapaciteBloc());
            if (bloc.getChambres()==null || bloc.getChambres().isEmpty()) ;
            log.info("pas du chambres sisponible dans ce blocs");
            bloc.getChambres().forEach(chambre -> {
                log.info("NumChambre: " +
                        chambre.getNumeroChambre()
                        +" type: " + chambre.getTypeC()
                );
            });
            log.info("*************************");
        }
    else{
        log.info("Aucun bloc trouve");
    }

}

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambres, Long idBloc) {

        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(() ->new RuntimeException("bloc intovable:"+idBloc));

        List<Chambre> chambres =  chambreRepsitory.findAllByNumeroChambreIn(numChambres);
if (chambres.size() != numChambres.size()) {
    throw  new RuntimeException("une ou plusieurs"+"chambre sont introuvable");

}
for (Chambre chambre : chambres)
    if (chambre.getBloc() != null && chambre.getBloc().getIdBloc()!=idBloc){
        throw  new RuntimeException("chambre "+chambre.getNumeroChambre()+" est deja affecte a un  autre bloc");
    }
for (Chambre chambre : chambres){
    chambre.setBloc(bloc);
}
if(bloc.getChambres() ==null)
    bloc.setChambres( new ArrayList<Chambre>());
   bloc.getChambres().addAll(chambres);
blocRepository.save(bloc);
chambreRepsitory.saveAll(chambres);
return bloc;




    }
    @Override
    public List<Bloc> retrieveAllBoc() {
        return blocRepository.findAll();

    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc).get();

    }

    @Override
     public void deleteBloc(Long idBloc){
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