package org.esprim.foyer.service;

import org.esprim.foyer.entity.Etudiant;

import java.util.List;

public interface EtudiantServiceI {
    public List<Etudiant>  getAllEtudiant();
    public Etudiant retrieveEtudiant(long idEtudiant);
    public void removeEtudiant(long id);
    public Etudiant modifyEtudiant (Etudiant etudiant);
    public Etudiant addEtudiant (Etudiant etudiant);



}
