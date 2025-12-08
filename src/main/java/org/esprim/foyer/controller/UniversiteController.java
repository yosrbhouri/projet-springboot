package org.esprim.foyer.controller;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Universite;
import org.esprim.foyer.service.UniversiteServiceI;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteController {

    UniversiteServiceI universiteService;



    @PostMapping("/affecter/{foyerId}")
    public Universite affecterUniversite(
            @PathVariable Long foyerId,
            @RequestParam String nomuniversite
    ) {
        Universite universite = universiteService.affecterUniversite(foyerId, nomuniversite);
        return universite;
    }
    @PutMapping("/desaffecter/{iduniversite}")
    public Universite desaffecterUniversite(
            @PathVariable Long iduniversite
    ) {
        Universite universite = universiteService.desaffecterUniversite(iduniversite);
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.getAllUniversiter();
        return listUniversites;
    }
    // http://localhost:8089/tpfoyer/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        Universite universite = universiteService.getUniversiteById(uId);
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/add-universite
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversite(u);
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/remove-universite/{universite-id}
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        universiteService.deleteUniversiteById(uId);
    }

    // http://localhost:8089/tpfoyer/universite/modify-universite
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.modifyUniversity(u);
        return universite;
    }

}

