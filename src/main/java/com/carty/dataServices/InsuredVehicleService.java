package com.carty.dataServices;

import java.util.List;

import com.carty.data.InsuredVehicle;
import com.carty.data.Vehicle;
import com.carty.model.User;

public interface InsuredVehicleService {

    public List<InsuredVehicle> getAllVehicles();
    
    public List<InsuredVehicle> getCustomerVehicles(User user);
    
    public List<InsuredVehicle> getCustomerVehiclesByUserId(long userId);
	
	public Vehicle findById(long id);
	
	public void createVehicle(InsuredVehicle vehicle);
	
	public void delete(InsuredVehicle vehicle);
	
	public void delete(long vehicleId);
	
	public void update(Vehicle vehicle);
	public void update(long vehicleId);

}
