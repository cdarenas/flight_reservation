package com.bharath.flightreservation.controllers;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repositories.FlightRepository;
import com.bharath.flightreservation.services.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/completeReservation")
    public String showCompleteReservation(@RequestParam("flightId") Integer flightId, Model model) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        model.addAttribute("flight", flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(@ModelAttribute ReservationRequest request, Model model) {
        Reservation reservation = reservationService.bookFlight(request);
        model.addAttribute("msg", "Reservation created successfully with id: " + reservation.getId());
        return "reservationConfirmation";
    }

}
