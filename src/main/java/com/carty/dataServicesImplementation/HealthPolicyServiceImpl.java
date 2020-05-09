package com.carty.dataServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.data.Account;
import com.carty.data.HealthPDB;
import com.carty.data.HealthPolicy;
import com.carty.log4j2.log4j2;
import com.carty.model.User;
import com.carty.repo.AccountRepo;
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
	@Autowired
	AccountRepo accr;
	@Autowired
	log4j2 logger;
	
	public User addPolicyToUser(long userId, long healthPDBId, double insuredValue) {
		User user = usi.findById(userId);
		HealthPDB hpdb = hpdbr.findById(healthPDBId).get();
		HealthPolicy hp = new HealthPolicy(insuredValue, hpdb);
		hp = hpr.save(hp);
		user.setHpolicy(hp);
		logger.doLog("Health Policy #"+hp.toString()+" has been added to user "+user.toString());
		return usi.save(user);
		
	}
	
	public HealthPolicy removeUserPolicy(long userId) {
		User user = usi.findById(userId);
		HealthPolicy hp = user.getHpolicy();
		hpr.delete(hp);
		
		logger.doLog("Health Policy #"+hp.toString()+" has been removed from user "+user.toString());
		/*may note be necessary because we used cascade all
		List<Account> accs = hp.getAccounts();
		accr.deleteAll(accs);
		hpr.delete(hp); */
		
		user.setHpolicy(null);
		usi.save(user);
		
		return hp;
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
    	
    	logger.doLog("Health Policy #"+hPolicyId+" approval status has been set to "+status);
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
