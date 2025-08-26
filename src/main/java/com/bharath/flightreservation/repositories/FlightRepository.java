package com.bharath.flightreservation.repositories;

import com.bharath.flightreservation.entities.Flight;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM Flight f WHERE f.departureCity = :from AND f.arrivalCity = :to AND f.dateOfDeparture = :departureDate")
    List<Flight> findFlights(@Param("from") String from,
            @Param("to") String to,
            @Param("departureDate") Date departureDate);

}