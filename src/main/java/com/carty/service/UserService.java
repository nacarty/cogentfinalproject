package com.carty.service;

import java.util.List;


import com.carty.model.User;
import com.carty.model.UserDto;



public interface UserService{

	    User save(UserDto user); //UserDto
		List<User> findAll();
		void delete(long id);
		User findOne(String email);
		User findById(Long id); //could be long instead of Long
		List<User> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname);
		//List<User> findByName(String fname, String lname); //already defined in the DAO
	
}
