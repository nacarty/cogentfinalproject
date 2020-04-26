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

import com.carty.data.HealthPDB;
import com.carty.data.HealthPolicy;
import com.carty.dataServicesImplementation.HealthPolicyServiceImpl;
import com.carty.model.User;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/health") 
public class HealthController {

	@Autowired
	HealthPolicyServiceImpl hpsi;
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public User addUserPolicy(@RequestParam("uid")  long uid, 
							  @RequestParam("hpid")  long hpid,
							  @RequestParam("inval")  long inval) {
	
		return hpsi.addPolicyToUser(uid, hpid, inval);
	
	}
	
	@RequestMapping(value = "/del/{uid}", method=RequestMethod.DELETE)
	public User removeUserPolicy(@PathVariable(value="uid") long uid) {
		
		return hpsi.removeUserPolicy(uid);
		
	}
	
	@RequestMapping(value = "/view/{uid}", method=RequestMethod.GET)
	public HealthPolicy viewUserPolicy(@PathVariable(value="uid") long uid) {
		return hpsi.viewUserPolicy(uid);
	}
	
	@RequestMapping(value = "/new", method=RequestMethod.POST)
	public HealthPDB addNewPolicyType(@RequestBody HealthPDB hpdb) {
		
		return hpsi.addNewPolicyType(hpdb.getName(), hpdb.getDescription(), hpdb.getBasePremium());
	}
	
	@RequestMapping(value = "/types", method=RequestMethod.GET)
	public List<HealthPDB> viewPolicyTypes(){
		
		return hpsi.viewPolicyTypes();
	}
	
	@RequestMapping(value = "/type/{tid}", method=RequestMethod.GET)
	public HealthPDB viewPolicyType(@PathVariable(value="tid") long tid){
		
		return hpsi.viewPolicyType(tid);
	}
	
	
	@RequestMapping(value = "/update", method=RequestMethod.PATCH)
	public HealthPDB updatePolicyType(@RequestBody HealthPDB hpdb){
		
		return hpsi.updatePolicyType(hpdb);
	}
}
