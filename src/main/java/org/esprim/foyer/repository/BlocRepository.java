package org.esprim.foyer.repository;

import org.esprim.foyer.entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {

}

