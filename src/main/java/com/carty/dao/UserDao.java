package com.carty.dao;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carty.model.User;


public interface UserDao extends CrudRepository<User, Long> { //see about this

	User findByEmail(String email);
}
