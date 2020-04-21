package com.carty.service;


import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carty.model.User;
import com.carty.model.UserDto;
import com.carty.dao.UserDao;

@Service(value="cartyUService")              //interface only
public class CartyUserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	CartyUserServiceImpl(){
		
	}
	
	
	public List<User> findAll(){
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}
	
	@Override
	public User findOne(String email) {
		return userDao.findByEmail(email);
	}
	
	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();  //.get() prevents us from having to cast to user
	}
	
	public User save(UserDto user) {
		
		User newUser = new User(user.fname, user.lname, user.email, 
				           bcryptEncoder.encode(user.password), user.address, user.dob);
		
		
		return userDao.save(newUser);
	}
}
