package com.carty.dataServicesImplementation;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.carty.data.Account;
import com.carty.data.InsuredVehicle;
import com.carty.data.Make;
import com.carty.data.Models;
import com.carty.data.Vehicle;
import com.carty.data.VehiclePDB;
import com.carty.data.VehiclePolicy;
import com.carty.log4j2.log4j2;
import com.carty.model.User;
import com.carty.repo.AccountRepo;
import com.carty.repo.InsuredVehicleRepo;
import com.carty.repo.MakeRepo;
import com.carty.repo.ModelsRepo;
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
	@Autowired
	MakeRepo mr;
	@Autowired
	ModelsRepo modr;
	@Autowired
	AccountRepo accr;
	@Autowired
	log4j2 logger;
	
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
		
		logger.doLog("Vehicle Policy #"+vp.toString()+" has been added to user "+user.toString());
		return usi.save(user);
	}
	
	
	public boolean toggleHealthPolicyApproval(long vpid, boolean status) {
		
		VehiclePolicy vp = vpr.findById(vpid).get();
		vp.setApproved(status);
		vp.setActive(status);
		vp = vpr.saveAndFlush(vp);
		
		logger.doLog("Vehicle Policy #"+vpid+" approval has been set to "+status);
		return vp.isApproved();
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
	
	public List<VehiclePolicy> getPolicies(long userId){
		User user = usi.findById(userId);
		List<VehiclePolicy> list =user.getVpolicies();
				
		 if (!list.isEmpty()) {
				Collections.sort(list, (p1, p2)->{ 
					  if (p1.getId() < (p2.getId()))
						  return -1;
					  else 
						  return 1;
							  });
				}
				
		 return list;
	}
	
	public InsuredVehicle updateInsuredVehicle(InsuredVehicle inveh) {
		return ivr.saveAndFlush(inveh);
	}
	
	public VehiclePolicy deletePolicy(long vpid) {
		
		
		VehiclePolicy vp = vpr.findById(vpid).get(); 
		vpr.delete(vp);
		
		/* MAY NOT BE NECESSARY because we used cascade all in POJO Entity
		//delete the associated accounts
		List<Account> accs = vp.getAccounts();
		accr.deleteAll(accs);
		
		//delete the associated insuredVehicle
		InsuredVehicle iVeh = vp.getVehicle();
		ivr.delete(iVeh);
		
		//now delete Vehicle policy
		vpr.delete(vp);
		*/
		logger.doLog("Vehicle Policy #"+vpid+" has been deleted from user.");
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
	
	public List<Make> findAllMakes() {
		
		return mr.findAllMakes();
	}
	
	public List<Make> findMakes(){
		return mr.findAll();
	}
	
	public List<Models>findModelsByMake(String make) {
		return modr.findByMake(make);
	}
	
	public List<Vehicle> findByMakeAndModel(String make, String model){
		return vehr.findByMakeAndModel(make, model);
	}
	
	public List<Vehicle> findVehicleByModel(String model){
		return vehr.findByModel(model);
	}
}