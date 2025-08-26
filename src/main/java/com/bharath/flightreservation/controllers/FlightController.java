package com.bharath.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;

@Controller
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/findFlights")
    public String displayFindFlights() {
        return "findFlights";
    }

    @PostMapping("/findFlights")
    public String displayFindFlights(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate,
            Model model) {

        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
        model.addAttribute("flights", flights);
        return "displayFlights";
    }
}
