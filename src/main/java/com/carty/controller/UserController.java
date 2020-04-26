package com.carty.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carty.model.User;
import com.carty.model.UserDto;
import com.carty.service.UserService;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService cartyUService;  //private UserService userService or we can use UserServiceImpl;
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public List<User> listUser(){
		return cartyUService.findAll();
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/user/{id}", method=RequestMethod.GET)
	public User getOne(@PathVariable(value = "id") Long id) {
		return cartyUService.findById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/email/{email}", method=RequestMethod.GET)
	public User getUserByEmail(@PathVariable(value = "email") String email) {
		return cartyUService.findOne(email);
	}
	
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public User saveUser(@RequestBody UserDto user) {
		System.out.println("Controller: "+user);
		return cartyUService.save(user);
	}
	
	@PostMapping("/signupp")
	public User saveUser2(@RequestBody UserDto user) {
		System.out.println("Controller: "+user);
		return cartyUService.save(user);
	}
	
	@PreAuthorize("hasRole('ROLE_AGENT')")
	@RequestMapping(value = "/name", method=RequestMethod.GET)
	List<User> findByFnameIgnoreCaseAndLnameIgnoreCase(@RequestParam("fname") String fname, @RequestParam("lname") String lname){
		return cartyUService.findByFnameIgnoreCaseAndLnameIgnoreCase(fname, lname);
	}
	public UserController() {
		
	}
}
