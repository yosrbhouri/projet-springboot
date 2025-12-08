package org.esprim.foyer.controller;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Etudiant;
import org.esprim.foyer.service.EtudiantServiceI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantContoller {
    EtudiantServiceI etudianterviceI;
    @GetMapping("/all-etudiant")
    public List<Etudiant> getAllEtudiant(){
        List<Etudiant> etudiants=etudianterviceI.getAllEtudiant();
        return etudiants;

    }
    @GetMapping("/etudiant/{id-etudiant}")
    public Etudiant retrieveEtudiantById(@PathVariable ("idEtudiant") Long idEtudiant ){
        Etudiant etudiant= etudianterviceI.retrieveEtudiant(idEtudiant);
        return etudiant;

    }

}
