package com.carty.dataServices;

import com.carty.data.Address;
import com.carty.model.User;

public interface AddressService {

		public Address getAddressByUser(User user);
		
		public Address getAddressById(long addressId);
		
		public void saveOrUpdate(Address address);
		
		
		public void delete(long addressId);
		
		public void delete(Address address);
		
		public void update(Address address);	

}
