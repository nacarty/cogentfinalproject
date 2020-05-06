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

import com.carty.data.Branch;
import com.carty.dataServicesImplementation.BranchServiceImpl;
import com.carty.model.User;
import com.carty.model.UserDto;


@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/branch") 
public class BranchController {

	
	@Autowired
	private BranchServiceImpl bsi; //we can use the interface as well as the implementation class
		
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(value = "/{bid}", method=RequestMethod.GET)
	public Branch getBranchById(@PathVariable(value="bid")  long bid) {
		
	         return bsi.getBranchById(bid);
		
	}
	

	@RequestMapping(value = "/setman", method=RequestMethod.POST)
	public Branch assignBranchManager(@RequestParam("bid") long bid, 
									 @RequestParam("mid") long mid) {
	
		Branch br = bsi.getBranchById(bid);
		br.setManagerId(mid);
		return bsi.saveBranch(br);
		
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public Branch createBranch(@RequestBody Branch br) { //Send a branch will no employees, but with a Branch Manager's ID
		
		return bsi.createBranch(br);
	}
	@RequestMapping(value = "/adduid", method=RequestMethod.POST)
	public User addUserById(@RequestParam("uid") long userId, 
			                @RequestParam("bid") long branchId) {

		return bsi.addUserById(userId, branchId);
	}
	

	@RequestMapping(value = "/adduser", method=RequestMethod.POST)
	public User addUser(@RequestBody UserDto user, @RequestParam("bid") long branchId) {
		
		return bsi.addUser(user, branchId);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void deleteBranch(@PathVariable(value="bid") long branchId) { //Send a branch will no employees, but with a Branch Manager's ID
		
		bsi.delete(branchId);
		
	}
	
	@RequestMapping(value="/user/{uid}", method=RequestMethod.GET)
	public long getUserBranch(@PathVariable("uid") long uid) {
		 return bsi.getUserBranch(uid);
	}
	
	@RequestMapping(value="/uid/{uid}", method=RequestMethod.GET)
	public Branch getBranchByUser(@PathVariable("uid") long uid) {
		 long bid = bsi.getUserBranch(uid);
		 return bsi.getBranchById(bid);
	}
	
}

