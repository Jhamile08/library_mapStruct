package com.riwi.library.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.library.domain.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    
}
