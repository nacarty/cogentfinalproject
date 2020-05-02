package com.carty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carty.data.Job;
import com.carty.dataServicesImplementation.JobServiceImpl;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/job") 
public class JobController {

	@Autowired
	JobServiceImpl jsi;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Job> findAllJobs(){
		return jsi.findAllJobs();
	}
}
