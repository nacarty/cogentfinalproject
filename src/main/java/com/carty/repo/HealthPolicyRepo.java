package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.HealthPolicy;


@Repository
public interface HealthPolicyRepo extends JpaRepository<HealthPolicy, Long> {

	
}