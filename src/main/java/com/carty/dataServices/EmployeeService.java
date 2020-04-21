package com.carty.dataServices;

import java.util.List;

import com.carty.data.Branch;
import com.carty.data.Employee;
import com.carty.data.Job;
import com.carty.model.User;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees(Branch branch);
	
	public List<Employee> getAllEmployees(long branchId);
	
	public List<Job> getEmployeeJobs(Branch branch);
	
	public List<Job> getEmployeeJobs(long branchId);
	
	public void createEmployee(User user);
	
	public void createEmployee();
	
	public void delete(Employee employee);
	
	public void delete(long employeeId);
	
	public void update(Employee employee);

}
