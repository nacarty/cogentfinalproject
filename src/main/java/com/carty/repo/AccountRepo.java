package com.carty.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.data.Account;


@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

	
}