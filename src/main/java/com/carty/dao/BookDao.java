package com.carty.dao;

import org.springframework.data.repository.CrudRepository;

import com.carty.model.Books;

public interface BookDao extends CrudRepository<Books, Long> {

}


