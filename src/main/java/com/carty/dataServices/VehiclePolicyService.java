package com.carty.dataServices;

import java.util.List;

import com.carty.data.Branch;
import com.carty.data.VehiclePolicy;

public interface VehiclePolicyService {

public List<VehiclePolicy> getAllPolicies(Branch branch);
	
	public List<VehiclePolicy> getAllPolicies(long branchId);
	
	public List<VehiclePolicy> getAllPolicies();
	
	public void createPolicy(VehiclePolicy policy);
	
	public void delete(VehiclePolicy policy);
	
	public void update(VehiclePolicy policy);

}
