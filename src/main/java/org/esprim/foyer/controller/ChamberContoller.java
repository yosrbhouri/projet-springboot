package org.esprim.foyer.controller;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.service.ChamberServiceI;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChamberContoller {
    ChamberServiceI chamberService;

    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chamberService.retrieveAllChambre();
        return listChambres;
    }
    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chamber = chamberService.retrieveChambre(chId);
        return chamber;


    }
    @GetMapping("/retrieve-type-chambre/{chambre-type}")
    public  Chambre retrieveTypeC(@PathVariable("chambre-type")String chambreType){
        Chambre  chambre =chamberService.retrieveTypeC(chambreType);
        return chambre;
    }


    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c){
        Chambre chamber= chamberService.addChambre(c);
        return chamber;
    }
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chamberService.removeChambre(chId);
    }
    // http://localhost:8089/tpfoyer/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chamber = chamberService.modifyChambre(c);
        return chamber;
    }

}