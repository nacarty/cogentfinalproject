package com.carty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carty.dataServicesImplementation.RoleServiceImpl;
import com.carty.model.Role;
import com.carty.model.User;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/role") 
public class RoleController {

	@Autowired
	private RoleServiceImpl rsi; //we can use the interface as well as this implementation class
		
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public User setUserRoles(@RequestParam("uid")  long uid, @RequestParam("rid")  long rid) {
		
		return rsi.setUserRoles(uid, rid);	
    }
	
	
	@RequestMapping(value = "/del", method=RequestMethod.DELETE)
	public User deleteUserRoles(@RequestParam("uid")  long uid, @RequestParam("rid")  long rid) {
		
		return rsi.deleteUserRoles(uid, rid);	
    }
	
	@RequestMapping(value = "/all", method=RequestMethod.GET)
	public List<Role> getAllRoles(){
		return rsi.findAllRoles();
	}
	
}
	
