package com.carty.service;

import java.util.List;

import com.carty.model.Role;
import com.carty.model.User;
import com.carty.model.UserDto;



public interface UserService{

	    User save(UserDto user); //UserDto
		List<User> findAll();
		User delete(long id);
		User findOne(String email);
		long getUserId(String email);
		User findById(Long id); //could be long instead of Long
		List<User> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname);
		List<User> findByAgentid(Long id);
		List<User> findByRoles(long roleId);
		
	
}
