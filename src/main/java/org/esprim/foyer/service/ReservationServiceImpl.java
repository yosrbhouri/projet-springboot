package org.esprim.foyer.service;

import lombok.AllArgsConstructor;
import org.esprim.foyer.entity.*;
import org.esprim.foyer.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@AllArgsConstructor
@Service

public class ReservationServiceImpl implements ReservationServiceI {
ReservationRepository reservationRepository;

   BlocRepository blocRepository;
    EtudiantRepository etudiantRepository;
     ChambreRepsitory chambreRepository;
     UniversiteRepository universiteRepository;

    @Override
    public Reservation ajouterReservation(Long idBloc, Long cinEtudiant) {

        // 1. Bloc
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé avec l'id: " + idBloc));

        // 2. Étudiant
        Etudiant etudiant = etudiantRepository.findById(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec le CIN: " + cinEtudiant));

        // 3. Chambre disponible
        Chambre chambreDisponible = trouverChambreDisponible(bloc);

        if (chambreDisponible == null) {
            throw new RuntimeException("Aucune chambre disponible dans le bloc: " + bloc.getNomBloc());
        }

        // 4. Année universitaire
        String anneeUniversitaire = getAnneeUniversitaire();

        // 5. Nouvelle réservation
        Reservation reservation = new Reservation();

        // ID réservation
        String idReservation = chambreDisponible.getNumeroChambre() + "-"
                + bloc.getNomBloc() + "-"
                + anneeUniversitaire;

        reservation.setIdReservation(idReservation);
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);

        // 6. Relations
        reservation.setChambre(chambreDisponible);

        // → Une réservation contient un set d'étudiants
        reservation.setEtudiants(Set.of(etudiant));

        // → Ajout dans la chambre
        if (chambreDisponible.getReservations() == null) {
            chambreDisponible.setReservations(new ArrayList<>());
        }
        chambreDisponible.getReservations().add(reservation);

        // → Ajout pour l’étudiant
        if (etudiant.getReservations() == null) {
            etudiant.setReservations(new HashSet<>());
        }
        etudiant.getReservations().add(reservation);

        // 7. Sauvegarde
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation annulerReservation(Long cinEtudiant) {

        // 1. Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findByCinEtudiant(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec le CIN : " + cinEtudiant));

        // 2. Trouver une réservation active associée
        Reservation reservation = etudiant.getReservations().stream()
                .filter(Reservation::isEstValide) // seulement les réservations valides
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucune réservation valide trouvée pour cet étudiant."));

        // 3. Mettre la réservation comme annulée
        reservation.setEstValide(false);

        // 4. Désaffecter l’étudiant de la réservation
        reservation.getEtudiants().remove(etudiant);

        // Désaffecter aussi la réservation du côté étudiant (référence bidirectionnelle)
        etudiant.getReservations().remove(reservation);

        // 5. Désaffecter la chambre associée
        Chambre chambre = reservation.getChambre();

        if (chambre != null) {
            chambre.getReservations().remove(reservation);
            chambreRepository.save(chambre);
        }

        // 6. Sauvegarder les modifications
        etudiantRepository.save(etudiant);
        return reservationRepository.save(reservation);
    }

    /**
     * Trouve une chambre disponible dans le bloc en vérifiant la capacité maximale
     * selon le type de chambre (SIMPLE=1, DOUBLE=2, TRIPLE=3)
     */
    private Chambre trouverChambreDisponible(Bloc bloc) {
        for (Chambre chambre : bloc.getChambres()) {
            int capaciteMax = getCapaciteMaxParType(chambre.getTypeC());
            int nombreReservationsActuelles = compterReservationsValides(chambre);

            // Vérifier si la capacité maximale n'est pas atteinte
            if (nombreReservationsActuelles < capaciteMax) {
                return chambre;
            }
        }
        return null;
    }

    /**
     * Retourne la capacité maximale selon le type de chambre
     */
    private int getCapaciteMaxParType(TypeChambre type) {
        switch (type) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Compte le nombre de réservations valides pour une chambre
     */
    private int compterReservationsValides(Chambre chambre) {
        if (chambre.getReservations() == null) {
            return 0;
        }
        return (int) chambre.getReservations().stream()
                .filter(Reservation::isEstValide)
                .count();
    }

    /**
     * Génère l'année universitaire au format "2024-2025"
     */
    private String getAnneeUniversitaire() {
        int year = LocalDate.now().getYear();
        return year + "-" + (year + 1);


    }

    /** Année universitaire : "2024-2025" */
    private String getCurrentAcademicYear() {
        int year = LocalDate.now().getYear();
        return year + "-" + (year + 1);
    }
    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(
            Date anneeUniversitaire, String nomUniversite) {

        // 1. Récupérer l'université par son nom (unique)
        Universite universite = universiteRepository.findByNomuniversite(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université introuvable : " + nomUniversite));

        // 2. Initialiser la liste des réservations filtrées
        List<Reservation> reservationsFiltrees = new ArrayList<>();


        // 3. Parcourir tous les foyers
        // Parcourir tous les blocs du foyer
       Foyer foyer = universite.getFoyer();
            for (Bloc bloc : foyer.getBlocs()) {

                // Parcourir toutes les chambres du bloc
                for (Chambre chambre : bloc.getChambres()) {

                    // Parcourir toutes les réservations de la chambre
                    for (Reservation res : chambre.getReservations()) {

                        // Filtrer par année universitaire
                        if (estMemeAnnee(res.getAnneeUniversitaire(), anneeUniversitaire)) {
                            reservationsFiltrees.add(res);
                        }
                    }
                    }
                }


        return reservationsFiltrees;
    }

    private boolean estMemeAnnee(Date date1, Date date2) {
        LocalDate d1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate d2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return d1.getYear() == d2.getYear();
    }

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(String reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void removeReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }


}
