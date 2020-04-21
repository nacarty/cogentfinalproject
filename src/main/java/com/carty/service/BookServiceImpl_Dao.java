package com.carty.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.carty.dao.BookDao;  //Based on CRUD instead of JPA repository
import com.carty.model.Books;

@Service(value="cartyBServiceDao")
public class BookServiceImpl_Dao {
	
	@Autowired
	BookDao repo;  //Based on CRUD instead of JPA repository
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Books> getAllBooks(){
		List<Books> list = new ArrayList<>();
		repo.findAll().forEach(books1->list.add(books1));
		return list;
		
	}
	
	@PreAuthorize("hasRole('USER')")
	public Books getBooksById(int bkId) {
		return repo.findById((long)bkId).get();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void saveOrUpdate(Books books) {
		repo.save(books);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void update(Books book) {
		repo.save(book);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public void saveAll(List<Books> b) {
	
		repo.saveAll(b);
		
	}

	
	@PreAuthorize("hasRole('ADMIN')")
	public void update(Books book, int id) {
		book.setBkId(id);
		repo.save(book);
		
	}
}
