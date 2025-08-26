package com.bharath.flightreservation.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FLIGHT_NUMBER", nullable = false)
    private String flightNumber;

    @Column(name = "OPERATING_AIRLINES", nullable = false)
    private String operatingAirlines;

    @Column(name = "DEPARTURE_CITY", nullable = false)
    private String departureCity;

    @Column(name = "ARRIVAL_CITY", nullable = false)
    private String arrivalCity;

    @Column(name = "DATE_OF_DEPARTURE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfDeparture;

    @Column(name = "ESTIMATED_DEPARTURE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedDepartureTime;
}
