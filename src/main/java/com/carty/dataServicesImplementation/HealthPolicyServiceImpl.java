package com.carty.dataServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.data.HealthPDB;
import com.carty.data.HealthPolicy;
import com.carty.model.User;
import com.carty.repo.HealthPDBRepo;
import com.carty.repo.HealthPolicyRepo;
import com.carty.service.CartyUserServiceImpl;

@Service(value="HealthPolicyService")
public class HealthPolicyServiceImpl {

	@Autowired 
	HealthPDBRepo hpdbr;
	@Autowired
	HealthPolicyRepo hpr;
	@Autowired
	CartyUserServiceImpl usi;
	
	public User addPolicyToUser(long userId, long healthPDBId, double insuredValue) {
		User user = usi.findById(userId);
		HealthPDB hpdb = hpdbr.findById(healthPDBId).get();
		HealthPolicy hp = new HealthPolicy(insuredValue, hpdb);
		hp = hpr.save(hp);
		user.setHpolicy(hp);
		return usi.save(user);
		
	}
	
	public User removeUserPolicy(long userId) {
		User user = usi.findById(userId);
		HealthPolicy hp = user.getHpolicy();
		user.setHpolicy(null);
		hpr.delete(hp);
		return usi.save(user);
	}
	
	public HealthPolicy viewUserPolicy(long userId) {
		
		return usi.findById(userId).getHpolicy();
	}
	
    public HealthPolicy viewPolicy(long hPolicyId) {
		System.out.println("**************We are here in HPSI searching for the Hpolicy***************");
		return hpr.findById(hPolicyId).get();
	}
	
    public boolean toggleHealthPolicyApproval(long hPolicyId, boolean status){
    	HealthPolicy hp = hpr.findById(hPolicyId).get();
    	hp.setApproved(status);
    	hp.setActive(status);
    	hp = hpr.saveAndFlush(hp);
    	return hp.getApproved();
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
	
}
