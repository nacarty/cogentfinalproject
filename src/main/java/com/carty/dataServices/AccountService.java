package com.carty.dataServices;

import java.util.List;

import com.carty.data.Account;
import com.carty.model.User;

public interface AccountService {

		public List<Account> getAllHealthAccounts();
		 
		public List<Account> getAllVehicleAccounts();
		
		public List<Account> getAccountsByUser(User user);
		
		public List<Account> getAccountsByUserId(Long userId);
		
		public Account getAccountById(long accountId);
		
		public void saveOrUpdate(Account account);
		
		public void delete(long accountId);
		
		public void update(Account account);

		public void saveAll(List<Account> accounts);
	
}
