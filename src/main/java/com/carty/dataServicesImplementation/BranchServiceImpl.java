package com.carty.dataServicesImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carty.dao.UserDao;
import com.carty.data.Address;
import com.carty.data.Branch;
import com.carty.data.HealthPDB;
import com.carty.data.HealthPolicy;
import com.carty.data.VehiclePDB;
import com.carty.data.VehiclePolicy;
import com.carty.dataServices.BranchService;
import com.carty.model.Role;
import com.carty.model.User;
import com.carty.model.UserDto;
import com.carty.repo.AddressRepo;
import com.carty.repo.BranchRepo;
import com.carty.repo.HealthPDBRepo;
import com.carty.repo.HealthPolicyRepo;
import com.carty.repo.RoleRepo;
import com.carty.service.CartyUserServiceImpl;

@Service(value="branchService") 
public class BranchServiceImpl implements BranchService{

	@Autowired
	BranchRepo br;
	@Autowired
	AddressRepo ar;
	@Autowired
	HealthPDBRepo hpdb;
	@Autowired
	HealthPolicyRepo hpr;
	@Autowired 
	RoleRepo rr;
	@Autowired
	CartyUserServiceImpl usi;
	
	public Branch getBranchById(long id) {
		return br.findById(id).get();
	}
	
	public Branch saveBranch(Branch b) {
		return br.save(b);
	}
	

	@Override
	public void delete(Branch branch) {
		
		br.delete(branch);
	}


	public Branch createBranch(Branch branch){
		
		Address address = branch.getAddress();
		address = ar.saveAndFlush(address);
		branch.setAddress(address);
		return br.saveAndFlush(branch);

	}
	
	public Branch createBranch(long managerId, Address address){

		Branch branch = new Branch(managerId, address);
		address = ar.saveAndFlush(address);
		branch.setAddress(address);
		return br.saveAndFlush(branch);
	}
	
	public User addUserById(long userId, long branchId) {
		 Branch b = br.findById(branchId).get();
			
		 User user = usi.findById(userId);
		 b.getUsers().add(user);
		 br.saveAndFlush(b);
		 
		 return user;
		
	}
	
	public User addUser(UserDto userdto, long branchId) {
				
		Branch b = br.findById(branchId).get();
		User user =usi.save(userdto);
		
		b.getUsers().add(user);
		br.saveAndFlush(b);
		
		return user;
	}
	
	public long getUserBranch(long uid) {//get USer Branch ID
		User user = new User();
		user.setId(uid);
	   return br.findByUsers(user).getId();
	}

	@Override
	public void delete(long branchId) {
		
		br.deleteById(branchId);
		
	}

	public Branch findByManagerId(long manid){
		return br.findByManId(manid);
	}
	
	
	/******************************************************/

	@Override
	public Branch getBranchByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch getBranchByManager(User manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllEmployees(Branch branch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HealthPDB> getAllHealthPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiclePDB> getAllVehiclePolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HealthPolicy> getAllHealthCustomerPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiclePolicy> getAllVehicleCustomerPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getManager(Branch branch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch createBranch(Address address, User manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Branch createBranch(Address address, long managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Address address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long branchId) {
		// TODO Auto-generated method stub
		
	}
}
