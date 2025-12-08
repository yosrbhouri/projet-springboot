package org.esprim.foyer.service;
import org.esprim.foyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationServiceI {

    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(String reservationId);
    public Reservation addReservation(Reservation r);
    public void removeReservation(String reservationId);
    public Reservation modifyReservation(Reservation reservation);
    public Reservation ajouterReservation(Long idBloc, Long cinEtudiant);
    public Reservation annulerReservation(Long cinEtudiant);

    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(
            Date anneeUniversitaire, String nomUniversite) ;

}
