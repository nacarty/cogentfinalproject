package com.carty.dataServices;

import java.util.List;

import com.carty.data.Models;

public interface ModelsService {

public List<Models> getAllModels();
	
	public Models findById(long id);
	
	public Models findByName(String modelName);
	
	public void createMake(Models model);
	
	//public void delete(Vehicle vehicle);
	
	//public void delete(long vehicleId);
	
	public void update(Models model);
	public void update(long makeId);

}
