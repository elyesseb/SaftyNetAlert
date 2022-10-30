package com.saftynetalert.saftynetalert.repositories;

import com.saftynetalert.saftynetalert.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findByName(String name);

}
