package com.carty.service;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carty.model.User;
import com.carty.model.UserDto;
import com.carty.repo.RoleRepo;
import com.carty.repo.AddressRepo;
import com.carty.dao.UserDao;
import com.carty.data.Address;


@Service(value="cartyUService")              //interface only
public class CartyUserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired 
	RoleRepo roleRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	CartyUserServiceImpl(){
		
	}
	
	
	public List<User> tryMethod(String fname, String lname) {
		
		return null; //userDao.findByName(fname, lname);
	}
	
	@Override
	public List<User> findAll(){
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	public User save(User user) {
		return userDao.save(user);
	}
	
	public List<User> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname){
		return userDao.findByFnameIgnoreCaseAndLnameIgnoreCase(fname, lname);
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
		
		Address address = addressRepo.save(user.address);
		User newUser = new User(user.fname, user.lname, user.email, 
				           bcryptEncoder.encode(user.password), address, user.dob);
		
		List<com.carty.model.Role> roles = new ArrayList<>();
		
		com.carty.model.Role role = roleRepo.findById(1L).get();
		
		roles.add(role);
		
		newUser.roles = roles;
	
		return userDao.save(newUser);
	}
}
