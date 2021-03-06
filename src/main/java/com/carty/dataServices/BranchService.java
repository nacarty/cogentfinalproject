package com.carty.dataServices;

import java.util.List;

import com.carty.data.Address;
import com.carty.data.Branch;
import com.carty.data.HealthPDB;
import com.carty.data.HealthPolicy;
import com.carty.data.VehiclePDB;
import com.carty.data.VehiclePolicy;
import com.carty.model.User;

public interface BranchService {

	public Branch getBranchByUser(User user);
	
	public Branch getBranchByManager(User manager);
	
	public List<User> getAllEmployees(Branch branch);
	
	public List<HealthPDB> getAllHealthPolicies();
	
	public List<VehiclePDB> getAllVehiclePolicies();
	
    public List<HealthPolicy> getAllHealthCustomerPolicies();
	
	public List<VehiclePolicy> getAllVehicleCustomerPolicies();
	
	public User getManager(Branch branch);
	
	public Branch createBranch(Address address, User manager);
	
	public Branch createBranch(Address address, long managerId);
	
	public void delete(Branch branch);
	
	public void delete(long branchId);
	
	public void update(Address address);
	
	public void update(long branchId);
}
