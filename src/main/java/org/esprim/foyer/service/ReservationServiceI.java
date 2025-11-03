package org.esprim.foyer.service;
import org.esprim.foyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationServiceI {

    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(long reservationId);
    public Reservation addReservation(Reservation r);
    public void removeReservation(long reservationId);
    public Reservation modifyReservation(Reservation reservation);


}
