package com.carty.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carty.dao.UserDao;
import com.carty.model.User;

@Service(value="cartyUDService")                //java class
public class CartyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
	User user = userDao.findByEmail(email);
	if (user == null) {
		throw new UsernameNotFoundException("User not found..");
	}
	return new org.springframework.security.core.userdetails.User(user.getEmail(),
			user.getPassword(), getAuthority(user));  // for the microservices, Instead of loading user credentials again, decode the credentials from the token
 }

private Set<SimpleGrantedAuthority> getAuthority(User user){ //for the microservices, we have to get the authorities from the token object (ie decode)
	Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	user.getRoles().forEach(role ->{
		authorities.add(new SimpleGrantedAuthority(role.getName()));  //I removed "ROLE_"+getName because in the database I put ROLE_ADMIN, ROLE_USER, etc
	});
			
	return authorities;
 }

}
