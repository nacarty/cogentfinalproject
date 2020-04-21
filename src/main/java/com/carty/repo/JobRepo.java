package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.Job;


@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

	
}