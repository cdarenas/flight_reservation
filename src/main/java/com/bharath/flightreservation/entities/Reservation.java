package com.bharath.flightreservation.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Setter
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CHECKED_IN")
    private Integer checkedIn;

    @Column(name = "NUMBER_OF_BAGS")
    private Integer numberOfBags;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight flight;

    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
