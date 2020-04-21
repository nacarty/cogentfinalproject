package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.Address;


@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

	
}