package com.carty.dataServices;

import java.util.List;


import com.carty.data.HealthPDB;

public interface HealthPDBService {

	public List<HealthPDB> getAllPolicies();
	
	
	public void createPolicy(HealthPDB policy);
	
	public void delete(HealthPDB policy);
	
	public void delete(long policyId);
	
	public void update(HealthPDB policy);

}
