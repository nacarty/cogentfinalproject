package com.carty.dataServices;

import java.util.List;

import com.carty.data.Branch;
import com.carty.data.Job;
import com.carty.model.User;

public interface EmployeeService {//Deal with users who are employees
	
	public List<User> getAllEmployees(Branch branch);
	
	public List<User> getAllEmployees(long branchId);
	
	public List<Job> getEmployeeJobs(Branch branch);
	
	public List<Job> getEmployeeJobs(long branchId);
	
	public void createEmployee(User user);
	
	public void createEmployee();
	
	public void delete(User employee);
	
	public void delete(long employeeId);
	
	public void update(User employee);

}
