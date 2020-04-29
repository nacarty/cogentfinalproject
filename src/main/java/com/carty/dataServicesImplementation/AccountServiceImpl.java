package com.carty.dataServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.data.Account;
import com.carty.data.HealthPolicy;
import com.carty.data.VehiclePolicy;
import com.carty.repo.AccountRepo;
import com.carty.repo.HealthPolicyRepo;
import com.carty.service.CartyUserServiceImpl;

@Service(value="AccountService")
public class AccountServiceImpl {

	@Autowired
	AccountRepo accr;	
	@Autowired
	CartyUserServiceImpl usi;
	@Autowired
	VehiclePolicyServiceImpl vpsi;
	@Autowired
	HealthPolicyServiceImpl hpsi;
	@Autowired
	HealthPolicyRepo hpr;
	
	
	
	public Account findById(long accid) { //whether Health or Vehicle -related accounts
		return accr.findById(accid).get();
	}
	
	public List<Account> getUserHealthAccounts(long userId){
	     	HealthPolicy hp = hpsi.viewUserPolicy(userId);
	     	return hp.getAccounts();
	}
	
	public List<Account> getHealthAccounts(long hPolicyId){
		HealthPolicy hp = hpr.findById(hPolicyId).get();
		return hp.getAccounts();
	}
	
	public List<Account> getVehicleAccounts(long vPolicyId){
		VehiclePolicy vp = vpsi.getPolicy(vPolicyId);
		return vp.getAccounts();
	}
	
	public Account makeHealthPayment(long userId, double payment) {
		HealthPolicy hp = hpsi.viewUserPolicy(userId);
		double balance = hp.getPremium();
		long id = 1;
		List<Account> accounts = hp.getAccounts();
		
		if (!accounts.isEmpty()) {
		  //Collections.sort(accounts);
		  Account last = accounts.get(accounts.size() - 1);
		  balance = last.getAmountBalance(); 
		  id = last.getId() + 1;
		}
		
		Account acc = new Account("HE:"+id, payment, balance-payment);
		acc = accr.saveAndFlush(acc);
		accounts.add(acc);
		hp.setAccounts(accounts);
		
		hpr.saveAndFlush(hp);
		return acc;
	}
	
	public Account makeVehiclePayment(long vPolicyId, double payment) {
		VehiclePolicy vp = vpsi.getPolicy(vPolicyId);
		double balance = vp.getPremium();
		long id = 1;
		List<Account> accounts = vp.getAccounts();
		
		if (!accounts.isEmpty()) {
		  //Collections.sort(accounts);
		  Account last = accounts.get(accounts.size() - 1);
		  balance = last.getAmountBalance(); 
		  id = last.getId() + 1;
		}
		
		Account acc = new Account("VE-"+vPolicyId+"-"+id, payment, balance-payment);
		acc = accr.saveAndFlush(acc);
		accounts.add(acc);
		vp.setAccounts(accounts);
		
		vpsi.updatePolicy(vp); 
		return acc;
	}
	
}
