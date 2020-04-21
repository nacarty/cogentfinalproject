package com.carty.dataServices;

import java.util.List;

import com.carty.data.Branch;
import com.carty.data.HealthPolicy;

public interface HealthPolicyService {

	public List<HealthPolicy> getAllPolicies(Branch branch);
	
	public List<HealthPolicy> getAllPolicies(long branchId);
	
	public List<HealthPolicy> getAllPolicies();
	
	public void createPolicy(HealthPolicy policy);
	
	public void delete(HealthPolicy policy);
	
	public void update(HealthPolicy policy);

}
