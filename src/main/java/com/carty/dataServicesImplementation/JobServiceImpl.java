package com.carty.dataServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.data.Job;
import com.carty.repo.JobRepo;

@Service(value="JobService")
public class JobServiceImpl {

	@Autowired
	JobRepo jr;
	
	public List<Job> findAllJobs(){
		return jr.findAll();
	}
}
