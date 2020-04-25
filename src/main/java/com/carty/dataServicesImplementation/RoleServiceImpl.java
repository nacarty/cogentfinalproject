package com.carty.dataServicesImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.model.Role;
import com.carty.model.User;
import com.carty.repo.RoleRepo;
import com.carty.service.CartyUserServiceImpl;

@Service(value="roleService")
public class RoleServiceImpl {

	@Autowired 
	RoleRepo rr;
	@Autowired
	CartyUserServiceImpl usi;
	
	public Role addNewRole(String name, String description) {//add new role to database
		
			return rr.saveAndFlush(new Role(name, description));
	}
	
	public User setUserRoles(long userId, long roleId) {
		 
		User user = usi.findById(userId);	
		user.roles = rr.findByIdLessThanEqual(roleId);
		
		return usi.save(user);
	}
	
	public User deleteUserRoles(long userId, long roleId) {
		 //if you delete a role for a user, delete all higher order roles as well
		//never delete the lowest level role from a user
		User user = usi.findById(userId);	
		if (roleId <= 1)
		{
			user.roles = new ArrayList<>();
			user.roles.add(rr.findById(1L).get());
		}
		else
			user.roles = rr.findByIdLessThan(roleId);
		
		return usi.save(user);
	}
	
	public List<Role> findAllRoles(){
		return rr.findAll();
	}
	
}
