package com.apap.tutorial7.repository;
import com.apap.tutorial7.model.PilotModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * PilotDB
 *
 */

@Repository
public interface PilotDB extends JpaRepository<PilotModel, Long> {
	Optional<PilotModel> findByLicenseNumber(String licenseNumber);

}
