package com.carty.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.carty.model.Role;
import com.carty.model.User;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	List<Role> findByIdLessThanEqual(long id);
	List<Role> findByIdLessThan(long id);
	@Override
	List<Role> findAll();
	List<User> findUsersById(long id);
}