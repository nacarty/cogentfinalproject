package com.carty.dataServices;

import java.util.List;

import com.carty.data.Vehicle;

public interface VehicleService {

    public List<Vehicle> getAllVehiclesByDescription(String make, String model, long year);
	
	public Vehicle findById(long id);
	
	public void createVehicle(Vehicle vehicle);
	
	//public void delete(Vehicle vehicle);
	
	//public void delete(long vehicleId);
	
	public void update(Vehicle vehicle);
	public void update(long vehicleId);

}
