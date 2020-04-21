package com.carty.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NigelController {

	//This is for demonstrative purposes only
	@RequestMapping({ "/jwt" })
	public String display() {
		return "Welcome to JWT after passing JWT in the header";
	}

}
