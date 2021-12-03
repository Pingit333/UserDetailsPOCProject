package com.mypoc.userdetailtracker.user.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mypoc.userdetailtracker.repo.UserRepository;
import com.mypoc.userdetailtracker.user.bean.Users;
import com.mypoc.userdetailtracker.user.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	// GET ---> URI : /users
	// retriveAllUsers()
	@GetMapping("/users")
	public List<Users> retriveAllUsers() {
		return userRepository.findAll();
	}

	// GET ---> URI : /users/{id}
	// retriveUserById(id)
	@GetMapping("/users/{id}")
	public Users retriveUserById(@PathVariable int id) {
		Optional<Users> user = userRepository.findById(id);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		} else {
			return user.get();
		}
	}

	// GET ---> URI : /users/name
	// retriveUserByName(name)
	@GetMapping("/users/name")
	public List<Users> retriveUserByName(@RequestParam String name) {
		List<Users> user = userRepository.findByName(name);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with : name - " + name);
		} else {
			return user;
		}

	}

	// GET ---> URI : /users/surname
	// retriveUserBySurName(name)
	@GetMapping("/users/surname")
	public List<Users> retriveUserBySurname(@RequestParam String surname) {
		List<Users> user = userRepository.findBySurname(surname);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with : surname - " + surname);
		} else {
			return user;
		}

	}

	// GET ---> URI : /users/pincode
	// retriveUserBySurName(pincode)
	@GetMapping("/users/pincode")
	public List<Users> retriveUserBySurname(@RequestParam int pincode) {
		List<Users> user = userRepository.findByPincode(pincode);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with : pincode - " + pincode);
		} else {
			return user;
		}
	}

	// POST ---> URI : /users
	// createUsers and send the status response as CREATED
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Users user) {

		Users createdUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // return /users
				.path("/{id}") // at place of {id}
				.buildAndExpand(createdUser.getId()) // it will set value of id
				.toUri(); // build URI /users/{id} ----> /users/4
		return ResponseEntity.created(uri).build(); // it will user URI and generate response 201 CREATED
	}

	// DELETE ---> URI : /users/{id}
	// deleteUser
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Users user = retriveUserById(id);
		if (user != null) {
			userRepository.deleteById(id);	
		}	
		else {
			throw new UserNotFoundException("id - " + id);
		}
	}

}
