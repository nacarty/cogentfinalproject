package com.carty.dataServices;

import java.util.List;

import com.carty.data.VehiclePDB;

public interface VehiclePDBService {

	public List<VehiclePDB> getAllPolicies();
	
	
	public void createPolicy(VehiclePDB policy);
	
	public void delete(VehiclePDB policy);
	
	public void delete(long policyId);
	
	public void update(VehiclePDB policy);

}
