package org.esprim.foyer.controller;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Bloc;
import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.entity.TypeChambre;
import org.esprim.foyer.service.BlocServiceI;
import org.esprim.foyer.service.ChamberServiceI;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChamberContoller {
    private final BlocServiceI blocServiceI;
    ChamberServiceI chamberService;
    @PostMapping("/affecter-chambres/{idBloc}")
    public ResponseEntity<?> affecterChambresABloc(
            @PathVariable Long idBloc,
            @RequestParam List<Long> numChambres
    ) {
        try {
            Bloc bloc = blocServiceI.affecterChambresABloc(numChambres, idBloc);
            return ResponseEntity.ok(bloc);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));

        }
    }
    @GetMapping("/non-reservees")
    public ResponseEntity<?> getChambresNonReservees(
            @RequestParam String nomUniversite,
            @RequestParam TypeChambre type) {

        try {
            List<Chambre> chambres =
                    chamberService.getChambresNonReserveParNomUniversiteEtTypeChambre(
                            nomUniversite, type);

            return ResponseEntity.ok(chambres);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> Chambres = chamberService.retrieveAllChambre();
        return Chambres;
    }
    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chamber = chamberService.retrieveChambre(chId);

        return chamber;


    }
    @GetMapping("/retrieve-type-chambre/{chambre-type}")
    public  Chambre retrieveTypeC(@PathVariable("chambre-type")String chambreType){

        return chamberService.retrieveTypeC(chambreType);
    }


    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c){

        return chamberService.addChambre(c);
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