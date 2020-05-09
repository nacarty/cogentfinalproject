package com.carty.dataServicesImplementation;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.data.Account;
import com.carty.data.HealthPolicy;
import com.carty.data.VehiclePolicy;
import com.carty.log4j2.log4j2;
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
	@Autowired
	log4j2 logger;
	
	public Account findById(long accid) { //whether Health or Vehicle -related accounts
		return accr.findById(accid).get();
	}
	
	public List<Account> getUserHealthAccounts(long userId){
	     	HealthPolicy hp = hpsi.viewUserPolicy(userId);
	     	return hp.getAccounts();
	}
	
	public List<Account> getHealthAccounts(long hPolicyId){
		System.out.println("**************We are here in ACI searching for the Hpolicy Accounts***************");
		HealthPolicy hp = hpsi.viewPolicy(hPolicyId);
		System.out.println(hp);
		return hp.getAccounts();
	}
	
	public List<Account> getVehicleAccounts(long vPolicyId){
		VehiclePolicy vp = vpsi.getPolicy(vPolicyId);
		return vp.getAccounts();
	}
	
	public Account makeHealthPayment(long hPolicyId, double payment, String memo) {
		HealthPolicy hp = hpsi.viewPolicy(hPolicyId);
		double balance = hp.getPremium();
		long id = 1;
		List<Account> accounts = hp.getAccounts();
		System.out.print("These are the health accounts:"+ accounts ); 
		if (!accounts.isEmpty()) {
		  Collections.sort(accounts, (a1, a2)->{ 
			  if (a1.getPaymentDate().before(a2.getPaymentDate()))
				  return -1;
			  else 
				  return 1;
					  });
		  Account last = accounts.get(accounts.size() - 1);

		  balance = last.getAmountBalance(); 
		  id = last.getId() + 1;
		  
		  System.out.print("Accounts should have been sorted.");
		}
		
		Account acc = new Account("HE:"+id, payment, balance-payment);
		if (memo.length() > 0)
		   acc.setMemo(memo);
		acc = accr.saveAndFlush(acc);
		accounts.add(acc);
		hp.setAccounts(accounts);
		
		logger.doLog("Payment made on Health Policy #"+hPolicyId+" in the amount of "+payment);
		hpr.saveAndFlush(hp);
		return acc;
	}
	
	
	public Account makeUserHealthPayment(long userId, double payment, String memo) {
		HealthPolicy hp = hpsi.viewUserPolicy(userId);
		double balance = hp.getPremium();
		long id = 1;
		List<Account> accounts = hp.getAccounts();
		
		if (!accounts.isEmpty()) {
			Collections.sort(accounts, (a1, a2)->{ 
				  if (a1.getPaymentDate().before(a2.getPaymentDate()))
					  return -1;
				  else 
					  return 1;
						  });
		
		  Account last = accounts.get(accounts.size() - 1);
		  balance = last.getAmountBalance(); 
		  id = last.getId() + 1;
		}
		
		Account acc = new Account("HE:"+id, payment, balance-payment);
		if (memo.length() > 0)
		   acc.setMemo(memo);
		acc = accr.saveAndFlush(acc);
		accounts.add(acc);
		hp.setAccounts(accounts);
		
		hpr.saveAndFlush(hp);
		logger.doLog("Payment made on Health Policy #"+hp.getId()+" in the amount of "+payment);
		return acc;
	}
	
	public Account makeVehiclePayment(long vPolicyId, double payment, String memo) {
		VehiclePolicy vp = vpsi.getPolicy(vPolicyId);
		double balance = vp.getPremium();
		long id = 1;
		List<Account> accounts = vp.getAccounts();
		
		if (!accounts.isEmpty()) {
			Collections.sort(accounts, (a1, a2)->{ 
				  if (a1.getPaymentDate().before(a2.getPaymentDate()))
					  return -1;
				  else 
					  return 1;
						  });
			
		  Account last = accounts.get(accounts.size() - 1);
		  balance = last.getAmountBalance(); 
		  id = last.getId() + 1;
		}
		
		Account acc = new Account("VE-"+vPolicyId+"-"+id, payment, balance-payment);
		if (memo.length() > 0)
		     acc.setMemo(memo);
		acc = accr.saveAndFlush(acc);
		accounts.add(acc);
		vp.setAccounts(accounts);
		
		vpsi.updatePolicy(vp); 
		logger.doLog("Payment made on Vehicle Policy #"+vPolicyId+" in the amount of "+payment);
		return acc;
	}
	
}
