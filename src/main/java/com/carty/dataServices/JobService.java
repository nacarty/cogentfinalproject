package com.carty.dataServices;

import java.util.List;

import com.carty.data.InsuredVehicle;
import com.carty.data.Job;
import com.carty.data.Vehicle;
import com.carty.model.User;

public interface JobService {

public List<Job> getAllJobs();
    
    public List<Job> getCustomerJobs(User user);
    
    public List<Job> getCustomerJobs(long userId);
	
	public Vehicle findById(long id);
	
	public void createJob(Job job);
	
	public void delete(Job job);
	
	public void delete(long jobId);

	public void update(Job job);

}
