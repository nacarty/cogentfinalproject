package com.carty.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carty.model.AuthToken;
import com.carty.model.LoginUser;
import com.carty.config.TokenProvider;



@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController { //The sole purpose of this class is to respond to the user with a generated token

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenProvider jwtTokenUtil;
	
	@RequestMapping(value = "/get", method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException{
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword() ) );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		
		//tokenService.saveToken(token);
		return ResponseEntity.ok(new AuthToken(token));
	}
	
}
