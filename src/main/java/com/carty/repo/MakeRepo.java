package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.Make;


@Repository
public interface MakeRepo extends JpaRepository<Make, Long> {

	
}