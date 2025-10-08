package org.esprim.foyer.repository;

import org.esprim.foyer.entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {

}
