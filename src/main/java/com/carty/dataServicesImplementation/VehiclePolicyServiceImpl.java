package com.carty.dataServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.data.InsuredVehicle;
import com.carty.data.Vehicle;
import com.carty.data.VehiclePDB;
import com.carty.data.VehiclePolicy;
import com.carty.model.User;
import com.carty.repo.InsuredVehicleRepo;
import com.carty.repo.VehiclePDBRepo;
import com.carty.repo.VehiclePolicyRepo;
import com.carty.repo.VehicleRepo;
import com.carty.service.CartyUserServiceImpl;

@Service(value="VehiclePolicyService")
public class VehiclePolicyServiceImpl {

	@Autowired 
	CartyUserServiceImpl usi;
	@Autowired
	VehiclePDBRepo vpdbr;
	@Autowired
	VehiclePolicyRepo vpr;
	@Autowired
	InsuredVehicleRepo ivr;
	@Autowired
	VehicleRepo vehr;
	
	
	public User addPolicyToUser(long userId, long vehiclePDBId, double insuredValue, InsuredVehicle iVeh) {
		
		long vid = iVeh.getVehicleBrand().getVehicleId();
		System.out.println("This is the vehicle Id:"+vid);
		Vehicle veh = vehr.findById(vid).get();
		System.out.println(veh);
		if (iVeh.getValue() <= 0)
			iVeh.setValue(veh.getPrice()*0.8);
		
		iVeh.setVehicleBrand(veh);
		iVeh.setVIN(iVeh.getVIN().toUpperCase());

		List<InsuredVehicle> test = ivr.findByVin(iVeh.getVIN());
		
		if (!test.isEmpty()) {
			User user = new User();
			user.setFname("You sent a vehicle with a duplicate Vin #:"+iVeh.getVIN());
			return user;
		}
		
		iVeh = new InsuredVehicle(iVeh.getVIN().toUpperCase(), iVeh.getColor(), iVeh.getValue(), veh);
		iVeh = ivr.saveAndFlush(iVeh);
		
		VehiclePDB vpdb = vpdbr.findById(vehiclePDBId).get();		
		VehiclePolicy vp = new VehiclePolicy(vpdb, insuredValue, iVeh);
		vp = vpr.saveAndFlush(vp);
		
		User user = usi.findById(userId);
		List<VehiclePolicy> vpolicies = user.getVpolicies();
		vpolicies.add(vp);
		user.setVpolicies(vpolicies);
		return usi.save(user);
	}
	
	public InsuredVehicle getInsuredVehicle(long id) {
		
		return ivr.findById(id).get();
	}
	
	public InsuredVehicle getInsuredVehicle(String vin) {
		
		return ivr.findByVin(vin).get(0);
	}
	
	public VehiclePolicy getPolicy(long id) {//user policy
		
		return vpr.findById(id).get();
	}
	
	public VehiclePolicy updatePolicy(VehiclePolicy vp) {//user policy
		
		return vpr.save(vp);
	}
	
	public InsuredVehicle updateInsuredVehicle(InsuredVehicle inveh) {
		return ivr.saveAndFlush(inveh);
	}
	
	public VehiclePolicy deletePolicy(long userId, long vpid) {
		
		User user = usi.findById(userId);
		VehiclePolicy vp = vpr.findById(vpid).get(); 
		List<VehiclePolicy> list = user.getVpolicies();
		InsuredVehicle inv = vp.getVehicle();
		
		list.remove(vp);
		user.setVpolicies(list);
		usi.save(user);
		
		ivr.delete(inv);
		vpr.delete(vp);	
		
		return vp;
	}
	
	public List<VehiclePDB> viewPolicyTypes(){
		return vpdbr.findAll();
	}
	
	public VehiclePDB viewPolicyType(long pdbid) {
		
		return vpdbr.findById(pdbid).get();
	}
	
	public VehiclePDB addPolicyType(String name, String description, double basePremium) {
		VehiclePDB vpdb = new VehiclePDB(name, description, basePremium);
		return vpdbr.saveAndFlush(vpdb);
		
	}
	
	/*
	
	public HealthPolicy viewUserPolicy(long userId) {
		
		return usi.findById(userId).getHpolicy();
	}
	
	public HealthPDB addNewPolicyType(String name, String description, double basePremium) {
		
		return hpdbr.saveAndFlush(new HealthPDB(name, description, basePremium));
		
	}
	
	public List<HealthPDB> viewPolicyTypes(){
		return hpdbr.findAll();
	}
	
	public HealthPDB viewPolicyType(long id){
		return hpdbr.findById(id).get();
	}
	
	
	
	public HealthPDB updatePolicyType(HealthPDB hpdb){
		return hpdbr.saveAndFlush(hpdb);
	}
	*/
}
