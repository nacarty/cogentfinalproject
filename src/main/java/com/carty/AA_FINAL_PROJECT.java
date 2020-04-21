package com.carty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


//This class normally has the name of the project but it has been copied from a previous project so the name is like this
@CrossOrigin(origins="*")
@SpringBootApplication
public class AA_FINAL_PROJECT {

	public static void main(String[] args) {
		SpringApplication.run(AA_FINAL_PROJECT.class, args);
	}

}
