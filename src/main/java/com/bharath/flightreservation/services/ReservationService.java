package com.bharath.flightreservation.services;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Passenger;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.FlightRepository;
import com.bharath.flightreservation.repositories.PassengerRepository;
import com.bharath.flightreservation.repositories.ReservationRepository;
import com.bharath.flightreservation.dto.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation bookFlight(ReservationRequest request) {
        // Fetch flight
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow();

        // Crear y guardar el pasajero
        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setEmail(request.getEmail());
        passenger.setPhone(request.getPhone());
        passenger = passengerRepository.save(passenger);

        // Crear y guardar la reserva
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(0);
        reservation.setNumberOfBags(0);

        return reservationRepository.save(reservation);
    }
}
