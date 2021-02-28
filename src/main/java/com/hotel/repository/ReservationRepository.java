package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

}
