package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.VehiclePDB;


@Repository
public interface VehiclePDBRepo extends JpaRepository<VehiclePDB, Long> {

	
}