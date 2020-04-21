package com.carty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carty.model.Books;

@Repository
public interface BookRepo extends JpaRepository<Books, Integer> {

	
}
