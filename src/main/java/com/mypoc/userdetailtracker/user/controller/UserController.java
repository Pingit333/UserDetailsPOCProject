package com.mypoc.userdetailtracker.user.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Users>> retriveAllUsers() {
		List<Users> user = userRepository.findAll();
		return new ResponseEntity<List<Users>>(user, HttpStatus.FOUND);
	}

	// GET ---> URI : /users/findbyid{id}
	// retriveUserById(id)
	@GetMapping("/users/findbyid")
	public ResponseEntity<Users> retriveUserById(@RequestParam int id) {
		Optional<Users> user = userRepository.findById(id);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		} else {
			return new ResponseEntity<Users>(user.get(), HttpStatus.FOUND);
		}
	}

	// GET ---> URI : /users/name
	// retriveUserByName(name)
	@GetMapping("/users/findbyname")
	public ResponseEntity<List<Users>> retriveUserByName(@Valid @RequestParam String name) {
		List<Users> user = userRepository.findByName(name);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with : name - " + name);
		} else {
			return new ResponseEntity<List<Users>>(user, HttpStatus.FOUND);
		}

	}

	// GET ---> URI : /users/surname
	// retriveUserBySurName(name)
	@GetMapping("/users/findbysurname")
	public ResponseEntity<List<Users>> retriveUserBySurname(@Valid @RequestParam String surname) {
		List<Users> user = userRepository.findBySurname(surname);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with : surname - " + surname);
		} else {
			return new ResponseEntity<List<Users>>(user, HttpStatus.FOUND);
		}

	}

	// GET ---> URI : /users/pincode
	// retriveUserBySurName(pincode)
	@GetMapping("/users/findbypincode")
	public ResponseEntity<List<Users>> retriveUserBySurname(@RequestParam int pincode) {
		List<Users> user = userRepository.findByPincode(pincode);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with : pincode - " + pincode);
		} else {
			return new ResponseEntity<List<Users>>(user, HttpStatus.FOUND);
		}
	}

	// POST ---> URI : /users
	// createUsers and send the status response as CREATED
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {

		Users createdUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // return /users
				.path("/{id}") // at place of {id}
				.buildAndExpand(createdUser.getId()) // it will set value of id
				.toUri(); // build URI /users/{id} ----> /users/4
		return ResponseEntity.created(uri).build(); // it will user URI and generate response 201 CREATED
	}

	// DELETE ---> URI : /users/{id}
	// harddeleteUser
	@DeleteMapping("/users/harddeletebyid")
	public ResponseEntity<Object> hardDeleteUserById(@RequestParam int id) {
		Optional<Users> user = userRepository.findById(id);
		if (!user.isEmpty()) {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			throw new UserNotFoundException("User not found with : id - " + id);
		}
	}

	// DELETE ---> URI : /users/{id}
	// softdeleteUser
	@DeleteMapping("/users/softdeletebyid")
	public ResponseEntity<Object> softDeleteUserById(@RequestParam int id) {
		Optional<Users> user = userRepository.findById(id);
		if (!user.isEmpty()) {
			user.get().setDeleted(Boolean.TRUE);
			userRepository.save(user.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			throw new UserNotFoundException("User not found with : id - " + id);
		}
	}
	
	// PUT ---> URI : /users/{id}
	// updateUserById(id)
	@PutMapping("/users/updatebyid")
	public ResponseEntity<Users> updateUserById(@PathVariable int id, @Valid @RequestBody Users user) {
		Optional<Users> checkuser = userRepository.findById(id);
		if (checkuser.isEmpty()) {
			throw new UserNotFoundException("User not found with : id - " + id);
		} else {
			checkuser.get().setName(user.getName());
			checkuser.get().setSurname(user.getSurname());
			checkuser.get().setPincode(user.getPincode());
			checkuser.get().setBirthDate(user.getBirthDate());
			checkuser.get().setDateOfJoining(user.getDateOfJoining());
			checkuser.get().setDeleted(user.isDeleted());
			userRepository.save(checkuser.get());
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // return /users
					.path("/{id}") // at place of {id}
					.buildAndExpand(checkuser.get().getId()) // it will set value of id
					.toUri(); // build URI /users/{id} ----> /users/4
			return ResponseEntity.created(uri).build(); // it will user URI and generate response 201 CREATED
		}
	}

	// GET ---> URI : /users/sortby?sortby=birthDate/dateOfJoining
	// retriveUserDetailsSortBy(sortby)
	@GetMapping("/users/sortby")
	public ResponseEntity<List<Users>> retriveUserDetailsSortby(@RequestParam String sortby) {
		List<Users> user = userRepository.findAll((Sort.by(Sort.Direction.ASC, sortby)));
		return new ResponseEntity<List<Users>>(user, HttpStatus.FOUND);
	}
	
}
