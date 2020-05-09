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

import com.carty.data.Account;
import com.carty.data.Memo;
import com.carty.dataServicesImplementation.AccountServiceImpl;
import com.carty.dataServicesImplementation.VehiclePolicyServiceImpl;


@CrossOrigin(origins="*", maxAge = 3600)
@RestController 
@RequestMapping("/acc")  
public class AccountController {

	@Autowired
    VehiclePolicyServiceImpl vspi;
	@Autowired
	AccountServiceImpl asi;
	
	@RequestMapping(value="/payh", method=RequestMethod.POST)
	public Account makeUserHealthPayment(@RequestParam("uid") long userId, @RequestParam("amt") double payment,
			                      @RequestBody Memo memo) {	
	
		return asi.makeUserHealthPayment(userId, payment, memo.getMemo());
	}
	
	@RequestMapping(value="/payv", method=RequestMethod.POST)
	public Account VehiclePayment(@RequestParam("vpid") long vPolicyId, @RequestParam("amt") double payment,
			                       @RequestBody Memo memo) {	
	
		return asi.makeVehiclePayment(vPolicyId, payment, memo.getMemo());
	}
	
	@RequestMapping(value="/geth/{hpid}", method=RequestMethod.GET)
	public List<Account> getHealthAccounts(@PathVariable(value="hpid") long hpid){
		
		return asi.getHealthAccounts(hpid);
	}
	
	@RequestMapping(value="/getv/{vpid}", method=RequestMethod.GET)
	public List<Account> getVehicleAccounts(@PathVariable(value="vpid") long vpid){
		
		return asi.getVehicleAccounts(vpid);
	}
	
	
}

