package org.esprim.foyer.service;

import org.esprim.foyer.service.FoyerServiceImpl;

import org.esprim.foyer.entity.Foyer;

import java.util.List;

public interface FoyerServiceI {

    public List<org.esprim.foyer.entity.Foyer> retrieveAllFoyers();
    public Foyer retrieveFoyer(Long foyerId);
    public Foyer addFoyer(Foyer f);
    public void removeFoyer(Long foyerId);
    public Foyer modifyFoyer(Foyer foyer);

    // Here we will add later methods calling keywords and methods calling JPQL

}
