package com.carty.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carty.data.Vehicle;


@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

	
	List<Vehicle> findByMakeAndModel(String make, String model);
	List<Vehicle> findByModel(String model);
}