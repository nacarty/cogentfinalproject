package com.carty.dao;

import java.util.List;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carty.model.User;


public interface UserDao extends CrudRepository<User, Long> { //see about this

	User findByEmail(String email);
	List<User> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname);
    List<User> findByAgentId(Long id);
	
	//generally, we can do find[Attribute]By[AttributeAttribute]
	//eg findHpolicyById(long id) because user has an hpolicy attribute which in turn has an id attribute
}
