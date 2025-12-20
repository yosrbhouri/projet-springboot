package org.esprim.foyer.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.esprim.foyer.entity.Chambre;
import org.esprim.foyer.entity.TypeChambre;
import org.esprim.foyer.repository.ChambreRepsitory;
import org.esprim.foyer.repository.UniversiteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ChamberServiceImpl implements ChamberServiceI {
    ChambreRepsitory chambreRepsitory;
    UniversiteRepository universiteRepository;
    @Scheduled(cron = "0/15 * * * * *")
    public void pourcentageChalbreParTypeChambre() {
        List<Chambre> chambreList = chambreRepsitory.findAll();
        int totalChambre = chambreList.size();
        log.info("Total chambre: " + totalChambre);
        if (totalChambre > 0) {

            Map<String,Integer> chambreMap = new HashMap<>();

            for (Chambre chambre : chambreList) {
                String type=String.valueOf(chambre.getTypeC());
                chambreMap.put(type, chambreMap.getOrDefault(type, 0) + 1);
                // Affichage des pourcentages
                for (Map.Entry<String, Integer> entry : chambreMap.entrySet()) {
                    double pourcentage = (entry.getValue() * 100.0) / totalChambre;
                    log.info("Type: " + entry.getKey() + " → " + String.format("%.2f", pourcentage) + "%");
                }}

        }

    }

    @Override
    public List<Chambre> retrieveAllChambre() {
        return chambreRepsitory.findAll();
    }

    @Override
    public Chambre retrieveChambre(Long chambreId ){
        return chambreRepsitory.findById(chambreId).get();
    }
    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(
            String nomUniversite, TypeChambre type) {

        // Année universitaire actuelle (ex : 2024–2025 → 2024)
        int anneeUniversitaire = java.time.LocalDate.now().getYear();

        return chambreRepsitory.getChambresNonReserveParNomUniversiteEtType(
                nomUniversite, type, anneeUniversitaire);
    }
    @Scheduled(cron = "0 */5 * * * *")
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        log.info("===== TACHE PLANIFIEE nbPlacesDisponibleParChambreAnneeEnCours EXECUTEE =====");

        int anneeEnCours = java.time.LocalDate.now().getYear();
        List<Chambre> chambres = chambreRepsitory.findAll();

        for (Chambre chambre : chambres) {

            int capacite;
            switch (chambre.getTypeC()) {
                case SIMPLE:
                    capacite = 1;
                    break;
                case DOUBLE:
                    capacite = 2;
                    break;
                case TRIPLE:
                    capacite = 3;
                    break;
                default:
                    capacite = 0;
            }

            long nbReservations = chambre.getReservations()
                    .stream()
                    .filter(r -> {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(r.getAnneeUniversitaire());
                        return cal.get(Calendar.YEAR) == anneeEnCours;
                    })

                    .count();

            int placesDisponibles = capacite - (int) nbReservations;

            if (placesDisponibles <= 0) {
                log.info("La chambre {} est complete", chambre.getNumeroChambre());
            } else {
                log.info("Le nombre de place disponible pour la chambre {} est {}",
                        chambre.getNumeroChambre(), placesDisponibles);
            }
        }
    }



    @Override
    public void removeChambre(long chambreId) {
         chambreRepsitory.deleteById(chambreId);
    }


    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepsitory.save(chambre);
    }

    public Chambre addChambre(Chambre c) {
        return chambreRepsitory.save(c);
    }
    @Override
    public Chambre retrieveTypeC(String typeChambre) {
        return chambreRepsitory.findByTypeC(TypeChambre.valueOf(typeChambre.toUpperCase()));
    }
    @Override
    public  List<Chambre> getChambreParNomUniversite(String nomUniversite){
return chambreRepsitory.getChambresParNomUniversite(nomUniversite);

    }
    @Override
    public List<Chambre> getChambresParBlocEtType (Long idBloc, TypeChambre
            typeC) {
        return chambreRepsitory.getChambresParBlocEtType(idBloc, typeC);
    }
}
