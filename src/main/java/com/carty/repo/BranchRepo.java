package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.Branch;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {

	
}