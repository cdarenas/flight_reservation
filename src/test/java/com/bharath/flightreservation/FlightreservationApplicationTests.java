package com.bharath.flightreservation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repositories.FlightRepository;

@SpringBootTest
@AutoConfigureMockMvc
class FlightreservationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightRepository flightRepository;

	@Test
	void testDisplayFindFlightsGet() throws Exception {
		mockMvc.perform(get("/findFlights"))
				.andExpect(status().isOk())
				.andExpect(view().name("findFlights"));
	}

	@Test
	void testDisplayFindFlightsPostWithResults() throws Exception {
		Flight flight = new Flight();
		flight.setId(1);
		List<Flight> flights = Arrays.asList(flight);

		Mockito.when(flightRepository.findFlights(anyString(), anyString(), any(Date.class)))
				.thenReturn(flights);

		mockMvc.perform(post("/findFlights")
				.param("from", "NYC")
				.param("to", "LAX")
				.param("departureDate", "2024-06-01"))
				.andExpect(status().isOk())
				.andExpect(view().name("displayFlights"))
				.andExpect(model().attribute("flights", hasSize(1)))
				.andExpect(model().attribute("flights", hasItem(flight)));
	}

	@Test
	void testDisplayFindFlightsPostWithNoResults() throws Exception {
		Mockito.when(flightRepository.findFlights(anyString(), anyString(), any(Date.class)))
				.thenReturn(Collections.emptyList());

		mockMvc.perform(post("/findFlights")
				.param("from", "NYC")
				.param("to", "LAX")
				.param("departureDate", "2024-06-01"))
				.andExpect(status().isOk())
				.andExpect(view().name("displayFlights"))
				.andExpect(model().attribute("flights", hasSize(0)));
	}

}
