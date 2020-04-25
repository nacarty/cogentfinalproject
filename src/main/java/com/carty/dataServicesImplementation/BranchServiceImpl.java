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
import com.carty.repo.AddressRepo;
import com.carty.repo.BranchRepo;
import com.carty.repo.HealthPDBRepo;
import com.carty.repo.HealthPolicyRepo;
import com.carty.repo.RoleRepo;

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
	UserDao udao;
	
	public Branch getBranchById(long id) {
		return br.findById(id).get();
	}
	
	public Branch saveBranch(Branch b) {
		return br.save(b);
	}
	

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
	public User addUser(long userId, long branchId) {
		 Branch b = br.findById(branchId).get();
		 User user = udao.findById(userId).get();
		 b.getUsers().add(user);
		 br.saveAndFlush(b);
		 return user;
		
	}
	public User addUser(User user, long branchId) {
		Branch b = br.findById(branchId).get();
		//set address; save it then retrieve it
		Address a = user.address;
		a = ar.saveAndFlush(a);
		user.address = a;
		
		//set healthPolicy; save it then retrieve it
		/*HealthPDB h = hpdb.findById(1L).get();
		HealthPolicy hp = user.getHpolicy(); 
		hp.setDetails(h);
		hp = hpr.saveAndFlush(hp);		
		user.setHpolicy(hp);
		*/
		
		Role r = rr.findById(1L).get();
		user.roles = new ArrayList<>();
		user.roles.add(r);
		
		List<User> list = b.getUsers();
		if (list == null) {
			list = new ArrayList<>();
		}
		user = udao.save(user);
		
		list.add(user);
		b.setUsers(list);
		
		br.saveAndFlush(b);
		
		return user;
	}

	@Override
	public void delete(long branchId) {
		
		br.deleteById(branchId);
		
	}

	public Branch getBranchByUser(User user) {
	   return null;
    }
	
	public Branch getBranchByManager(User manager){
		return null;
	}
	
	public List<User> getAllEmployees(Branch branch){
		return null;
	}
	
	public List<HealthPDB> getAllHealthPolicies(){
		return null;
	}
	
	public List<VehiclePDB> getAllVehiclePolicies(){
		return null;
	}
	
    public List<HealthPolicy> getAllHealthCustomerPolicies(){
    	return null;
    }
	
	public List<VehiclePolicy> getAllVehicleCustomerPolicies(){
		return null;
	}
	
	
	
	public void update(long branchId){
	
	}

	@Override
	public void update(Address address) {
		
		
	}

	@Override
	public Branch createBranch(Address address, long managerId) {
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

}
