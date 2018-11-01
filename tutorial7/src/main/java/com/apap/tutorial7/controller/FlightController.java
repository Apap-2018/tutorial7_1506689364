package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



/**
 * 
 * FlightController
 *
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restAirport() {
		return new RestTemplate();
	}
	
	@GetMapping(value = "/airport/{city}")
	public String getAirportByCity(@PathVariable("city") String city) throws Exception{
		String path = Setting.airportUrl + "&term=" + city;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	
	@PostMapping(value = "/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {	
        return flightService.addFlight(flight);
	}
	
	@DeleteMapping(value = "/delete")
	public String deleteFlight(@RequestParam("flightId") long flightId) {
		FlightModel flight = flightService.getFlightDetailById(flightId).get();
		flightService.deleteFlight(flight);
		return "Flight has been deleted";
	}
	
	@GetMapping(value = "/view/{flightNumber}")
	public FlightModel flightView(@PathVariable(value = "flightNumber") String flightNumber) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber).get();
		return flight;
	}
	
	@GetMapping("/all")
	public List<FlightModel> viewAllFlight() {
		List<FlightModel> flight = flightService.getFlightList();
		return flight;
	}
	
	@PutMapping(value = "/update/{flightId}")
	private String updateFlightSubmit(@PathVariable("flightId") long flightId,
									@RequestParam(value = "destination") String destination,
									@RequestParam(value = "origin") String origin,
									@RequestParam(value = "time") Date time) {
		
		FlightModel flight = flightService.getFlightDetailById(flightId).get();
		
		if(flight.equals(null)) {
			return "Couldn't find your flight";
		}
		
		flight.setDestination(destination);
		flight.setOrigin(origin);
		flight.setTime(time);
		
		flightService.addFlight(flight);
		
		return "Flight update success";
	}
	
	
}
