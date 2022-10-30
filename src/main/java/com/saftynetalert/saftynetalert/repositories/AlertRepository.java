package com.saftynetalert.saftynetalert.repositories;

import com.saftynetalert.saftynetalert.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
