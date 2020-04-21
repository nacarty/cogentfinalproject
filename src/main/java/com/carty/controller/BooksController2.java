package com.carty.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carty.model.Books;
import com.carty.service.BookService;
import com.google.gson.*;//I used this to get my project to read either an array of JSON objects or a single JSON object from POSTMAN

//NOTE IMPORTANTLY THAT THIS IS A RESTFUL APPLICATION.
//THEREFORE, USE POSTMAN TO INTERFACE WITH THIS REST APPLICATION
//USE THE METHODS (GET, POST, UPDATE, DELETE) THAT PREFIX THE MAPPINGS BELOW
//IN ADDITION TO USING POSTMAN, YOU MAY ACCESS FROM - http://localhost:8080/swagger-ui.html - BECAUSE WE USED SWAGGER

@CrossOrigin(origins="*", maxAge=3600)  //this is to allow my Angular "BOOKS" project to access this module
@RestController
@RequestMapping("/books2") 
public class BooksController2 {
	
    @Autowired
	BookService cartyBServiceDao; //Based on CRUD instead of JPA repository
	
    
	@GetMapping("/allBooks") 
	private List <Books> getAllBooks(){
		return cartyBServiceDao.getAllBooks();
	}
    
    
	@RequestMapping(value = "/allMyBooks", method=RequestMethod.GET)
    private List <Books> getAllMyBooks(){
		return cartyBServiceDao.getAllBooks();
	}
	
   
	@GetMapping("/{bkId}")
	private Books getBooksById(@PathVariable("bkId") int bkId){
		return cartyBServiceDao.getBooksById(bkId);
	}
	
   
	@DeleteMapping("/{bkId}")
	private void deleteBookById(@PathVariable("bkId") int bkId){
    	cartyBServiceDao.delete(bkId);
	}
	
 
	@PostMapping("/save")
	public int saveBook(@RequestBody Books books) {
    	cartyBServiceDao.saveOrUpdate(books);
		return books.getBkId();
	}
	
    
	@PostMapping("/savemany")
	public  void saveBooksOneByOne(@RequestBody Collection<Books> books) {
		List <Books> L = new ArrayList<>(books);
		L.forEach((book)->cartyBServiceDao.saveOrUpdate(book));		
	}
	
    
	@PostMapping("/savemany2")
	public  void saveManyBooksAtOnce(@RequestBody Collection<Books> collection) {
		List <Books> B = new ArrayList<>(collection);
		cartyBServiceDao.saveAll(B);		
	}
	
    
	@PostMapping("/manychoice")
	public  void saveOneOrMoreBooks(@RequestBody String jsonString) {
		Gson g = new Gson();
		
		
		try {
			Books book = g.fromJson(jsonString, Books.class);
			cartyBServiceDao.saveOrUpdate(book);
			System.out.println(book);
		}
		catch(Exception e) {
			System.out.println("The JSON could not be cast to a single book object..Let's try to see if it is an array of boks instead.. ");
			try {
				Books [] bookArray = g.fromJson(jsonString, Books[].class );
				List<Books> bookList = Arrays.asList(bookArray);
				
				//ArrayList<Books> bookList = g.fromJson(jsonString, ArrayList<Books>.class );
				cartyBServiceDao.saveAll(bookList);
				bookList.forEach(System.out::println);
			}
			catch(Exception ex) {
				System.out.println("The JSON could not be cast to an array of books");
				ex.printStackTrace();
			}
		}
		
	}
	
	@PutMapping("/books")
	public Books update(@RequestBody Books books) {
    	cartyBServiceDao.saveOrUpdate(books);
		return books;
	}	
	
}
