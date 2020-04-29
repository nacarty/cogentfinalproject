package com.carty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carty.data.Account;
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
	public Account HealthPayment(@RequestParam("uid") long userId, @RequestParam("amt") double payment) {	
	
		return asi.makeHealthPayment(userId, payment);
	}
	
	@RequestMapping(value="/payv", method=RequestMethod.POST)
	public Account VehiclePayment(@RequestParam("vpid") long vPolicyId, @RequestParam("amt") double payment) {	
	
		return asi.makeVehiclePayment(vPolicyId, payment);
	}
	
	
}
