package com.carty.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carty.data.Branch;
import com.carty.model.Role;
import com.carty.model.User;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {

	Branch findByUsers(User user);
	
	List<Branch> findByIdAndUsersRoles(long id, Role r);
	
	@Query(value=" SELECT * FROM Branch b WHERE b.managerId = ?1", nativeQuery = true)
	Branch findByManId(Long manid);
	
	
}