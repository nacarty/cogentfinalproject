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
	
	public Role addNewRole(String name, String description) {//add new role to database. Not adding a role to a user
		
			return rr.saveAndFlush(new Role(name, description));
	}
	
	public User setUserRoles(long userId, long roleId) {
		 List<Role> roles = rr.findByIdLessThanEqual(roleId);
		User user = usi.findById(userId);	
		user.setRoles(roles); //= rr.findByIdLessThanEqual(roleId);
		
		return usi.save(user);
	}
	
	public User deleteAgent(long agentId, long newAgentId ) {//should be used to delete ny user with role higher than client
			
			List <User> agentClients = usi.findByAgentid(agentId);
			
			for (User u:agentClients)
				u.setAgentId(newAgentId);//user's agent id changed to the new agent to take over
			
			usi.saveAll(agentClients);
		
			return deleteUserRoles(agentId, 2);
	
	}
	
	public User deleteUserRoles(long userId, long roleId) {
		 //if you delete a role for a user, delete all higher order roles as well the role passed
		User user = usi.findById(userId);	
		if (roleId <= 1)
		{		
			return usi.delete(userId);  ///this will take care of deleting all user resources	
		}
		else 
		{
		   user.setRoles(rr.findByIdLessThan(roleId));
		   return usi.save(user);
		}
	}
	
	public List<Role> findAllRoles(){
		return rr.findAll();
	}
	
}
