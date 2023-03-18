package com.demo.dblockingpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://www.baeldung.com/jpa-pessimistic-locking
@SpringBootApplication
public class DBLockingPOCApplication {

	public static void main(String[] args) {
		SpringApplication.run(DBLockingPOCApplication.class, args);
	}

}
