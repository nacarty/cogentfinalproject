package com.carty.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carty.data.Make;



@Repository
public interface MakeRepo extends JpaRepository<Make, Long> {

	@Query(value = "SELECT make FROM makes m", 
			  nativeQuery = true)
	List<Make> findAllMakes();
	

}