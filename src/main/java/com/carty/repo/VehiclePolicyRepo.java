package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.VehiclePolicy;


@Repository
public interface VehiclePolicyRepo extends JpaRepository<VehiclePolicy, Long> {

	
}