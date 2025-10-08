package org.esprim.foyer.service;

import org.esprim.foyer.entity.Bloc;


import java.util.List;

public interface BlocServiceI {
    public List<Bloc> retrieveAllBoc();
    public  Bloc retrieveBloc(long idBloc);
    public void deleteBloc(long idBloc);
    public Bloc modifyBloc(Bloc bloc);
    public Bloc addBloc(Bloc bloc);


}
