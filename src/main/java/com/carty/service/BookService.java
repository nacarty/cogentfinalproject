package com.carty.service;

import java.util.List;

import com.carty.model.Books;

public interface BookService {

	public List<Books> getAllBooks();
	
	public Books getBooksById(int bkId);
	
	public void saveOrUpdate(Books books);
	
	
	public void delete(int id);
	
	public void update(Books books, int id);

	public void saveAll(List<Books> b);
}
