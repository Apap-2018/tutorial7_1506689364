package com.apap.tutorial7.repository;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * FlightDb
 *
 */
@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
	Optional<FlightModel> findByFlightNumber(String flightNumber);

}
