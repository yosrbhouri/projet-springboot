package org.esprim.foyer.repository;

import org.esprim.foyer.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {

}
