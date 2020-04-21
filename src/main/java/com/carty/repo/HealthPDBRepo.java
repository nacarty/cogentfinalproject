package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.HealthPDB;


@Repository
public interface HealthPDBRepo extends JpaRepository<HealthPDB, Long> {

	
}