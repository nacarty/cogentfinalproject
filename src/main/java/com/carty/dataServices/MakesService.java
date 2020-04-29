package com.carty.dataServices;

import java.util.List;

import com.carty.data.Make;

public interface MakesService {

	public List<Make> getAllMakes();
	
	public Make findById(long id);
	
	public Make findByName(String name);
	
	public void createMake(Make vehicleMake);
	
	//public void delete(Vehicle vehicle);
	
	//public void delete(long vehicleId);
	
	public void update(Make vehicleMake);
	public void update(long makeId);

}
