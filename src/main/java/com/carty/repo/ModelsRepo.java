package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.Models;


@Repository
public interface ModelsRepo extends JpaRepository<Models, Long> {

	
}