package com.carty.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.carty.dao.BookRepo;
import com.carty.model.Books;

@Service(value="cartyBService")
public class BookServiceImpl implements BookService{  //implements UserDetailsService
	
	@Autowired
	BookRepo repo;
	
	 @PreAuthorize("hasRole('USER')")
	public List<Books> getAllBooks(){
		List<Books> list = new ArrayList<>();
		repo.findAll().forEach(books1->list.add(books1));
		return list;
		
	}
	
	 @PreAuthorize("hasRole('USER')")
	public Books getBooksById(int bkId) {
		return repo.findById(bkId).get();
	}
	
	 @PreAuthorize("hasRole('ADMIN')")
	public void saveOrUpdate(Books books) {
		repo.save(books);
	}
	
	 @PreAuthorize("hasRole('ADMIN')")
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	 @PreAuthorize("hasRole('ADMIN')")
	public void update(Books books, int id) {
		repo.save(books);
	}

	 @PreAuthorize("hasRole('ADMIN')")
	public void saveAll(List<Books> b) {
	
		repo.saveAll(b);
		
	}
}
