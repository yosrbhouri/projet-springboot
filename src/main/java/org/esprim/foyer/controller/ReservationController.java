package org.esprim.foyer.controller;



import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.Reservation;
import org.esprim.foyer.service.ReservationServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    ReservationServiceI reservationService;

    @GetMapping("/par-annee-et-universite")
    public ResponseEntity<?> getReservationParAnneeEtUniversite(
            @RequestParam("annee") Date anneeUniversitaire,
            @RequestParam("universite") String nomUniversite) {

        try {
            List<Reservation> reservations =
                    reservationService.getReservationParAnneeUniversitaireEtNomUniversite(
                            anneeUniversitaire,
                            nomUniversite
                    );

            return ResponseEntity.ok(reservations);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    // http://localhost:8089/tpfoyer/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }
    // http://localhost:8089/tpfoyer/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String rId) {
        Reservation reservation = reservationService.retrieveReservation(rId);
        return reservation;
    }


    @PostMapping("/add/{idBloc}/{cinEtudiant}")
    public ResponseEntity<?> ajouterReservation(
            @PathVariable Long idBloc,
            @PathVariable Long cinEtudiant) {

        try {
            Reservation reservation =
                    reservationService.ajouterReservation(idBloc, cinEtudiant);

            return ResponseEntity.ok(reservation);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }



    // http://localhost:8089/tpfoyer/reservation/add-reservation
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.addReservation(r);
        return reservation;
    }

    // http://localhost:8089/tpfoyer/reservation/remove-reservation/{reservation-id}
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String rId) {
        reservationService.removeReservation(rId);
    }

    // http://localhost:8089/tpfoyer/reservation/modify-reservation
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.modifyReservation(r);
        return reservation;
    }

}

