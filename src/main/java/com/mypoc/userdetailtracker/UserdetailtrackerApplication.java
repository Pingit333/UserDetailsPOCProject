package com.mypoc.userdetailtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mypoc.userdetailtracker.repo.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class UserdetailtrackerApplication {

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(UserdetailtrackerApplication.class, args);
	}

}
