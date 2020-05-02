package com.carty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carty.data.InsuredVehicle;
import com.carty.data.VehiclePDB;
import com.carty.data.VehiclePolicy;
import com.carty.dataServicesImplementation.VehiclePolicyServiceImpl;
import com.carty.model.User;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/veh") 
public class VehicleController {

	@Autowired
    VehiclePolicyServiceImpl vspi;
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public User addPolicyToUser(@RequestParam("uid") long userId, @RequestParam("vpid") long vehiclePDBId, 
							    @RequestParam("inval") double insuredValue, @RequestBody InsuredVehicle iVeh) {

		return vspi.addPolicyToUser(userId, vehiclePDBId, insuredValue, iVeh);
   }
	
	@RequestMapping(value="/view/{uid}", method=RequestMethod.GET)
	public List<VehiclePolicy> getPolicies(@PathVariable(value="uid") long uid){
		
		return vspi.getPolicies(uid);
	}
	
	@RequestMapping(value="/insid/{inid}", method=RequestMethod.GET)
    public InsuredVehicle getInsuredVehicle(@PathVariable("insid") long id) {
		
		return vspi.getInsuredVehicle(id);
	}
	
	@RequestMapping(value="/vin/{vin}", method=RequestMethod.GET)
    public InsuredVehicle getInsuredVehicle(@PathVariable("vin") String vin) {
		return vspi.getInsuredVehicle(vin);
	}
	
	@RequestMapping(value="/pid/{pid}", method=RequestMethod.GET)
	public VehiclePolicy getPolicy(@PathVariable("pid") long pid) {
		return vspi.getPolicy(pid);
	}
	
	@RequestMapping(value="/updateupol", method=RequestMethod.POST)
	public VehiclePolicy updatePolicy(@RequestBody VehiclePolicy vp) {//user policy
		return vspi.updatePolicy(vp);
	}
	
	@RequestMapping(value="/updateinv", method=RequestMethod.POST)
	public InsuredVehicle updateInsuredVehicle(@RequestBody InsuredVehicle inveh) {//user policy
		return vspi.updateInsuredVehicle(inveh);
	}
	
	@RequestMapping(value="/del", method=RequestMethod.DELETE)
	public VehiclePolicy deletePolicy(@RequestParam("uid") long uid, @RequestParam("pid") long vpid) {
	
		return vspi.deletePolicy(uid, vpid);
	}
	
	@RequestMapping(value="/pdb", method=RequestMethod.GET)
	public List<VehiclePDB> viewPolicyTypes(){
		return vspi.viewPolicyTypes();
	}
	
	@RequestMapping(value="/pdb/{pdbid}", method=RequestMethod.GET)
	public VehiclePDB viewPolicyType(@PathVariable("pdbid") long pdbid){
		return vspi.viewPolicyType(pdbid);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)	
	public VehiclePDB addPolicyType(@RequestBody VehiclePDB vpdb) {
	
	  return vspi.addPolicyType(vpdb.getName(), vpdb.getDescription(), vpdb.getBasePremium());
    }
}