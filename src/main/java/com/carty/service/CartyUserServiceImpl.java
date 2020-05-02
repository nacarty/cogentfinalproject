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
	CartyMailService cms;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	CartyUserServiceImpl(){
		
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
	
	@Override
	public List<User> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname){
		return userDao.findByFnameIgnoreCaseAndLnameIgnoreCase(fname, lname);
	}
	@Override
	public List<User> findByAgentid(Long id){
		return userDao.findByAgentId(id);
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
	
	@Override
	public User save(UserDto user) {
		
		Address address = addressRepo.save(user.address);
		
		User newUser = new User(user.fname, user.lname, user.email, user.SSN,
				           bcryptEncoder.encode(user.password), address, user.dob);
		newUser.setAgentId(user.agentId);
		
		List<com.carty.model.Role> roles = new ArrayList<>();
		
		com.carty.model.Role role = roleRepo.findById(1L).get();
		
		roles.add(role);
		
		newUser.roles = roles;
	    cms.sendEmail(user);
		return userDao.save(newUser);
	}

	@Override
	public long getUserId(String email) {
		return findOne(email).getId();
	}
}
