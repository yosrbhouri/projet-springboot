package org.esprim.foyer.repository;

import org.esprim.foyer.entity.Foyer;
import org.esprim.foyer.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {



}
