package org.esprim.foyer.service;

import org.esprim.foyer.entity.Bloc;


import java.util.List;

public interface BlocServiceI {
    public List<Bloc> retrieveAllBoc();
    public  Bloc retrieveBloc(Long idBloc);
    public void deleteBloc(Long idBloc);
    public Bloc modifyBloc(Bloc bloc);
    public Bloc addBloc(Bloc bloc);
    public  Bloc affecterChambresABloc(List<Long> numChambres, Long idBloc);
public void  listChambreBlocs();

}
