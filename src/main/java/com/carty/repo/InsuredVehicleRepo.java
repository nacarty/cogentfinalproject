package com.carty.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.InsuredVehicle;


@Repository
public interface InsuredVehicleRepo extends JpaRepository<InsuredVehicle, Long> {

	List<InsuredVehicle> findByVin(String vin);
}