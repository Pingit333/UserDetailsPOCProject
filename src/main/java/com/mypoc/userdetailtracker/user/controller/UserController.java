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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mypoc.userdetailtracker.repo.UserRepository;
import com.mypoc.userdetailtracker.user.bean.Users;
import com.mypoc.userdetailtracker.user.exception.UserNotFoundException;
import com.mypoc.userdetailtracker.user.pojo.UsersDTO;

@RestController
public class UserController {
	
	private static final String MESSAGE = "User not found with : ";  
	private static final String ID = "id - ";
	private static final String NAME = "name - ";
	private static final String PINCODE = "pincode - ";
	private static final String SURNAME = "surname - ";
	
	@Autowired
	UserRepository userRepository;

	/**
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<List<Users>> retriveAllUsers() {
		List<Users> user = userRepository.findAll();
			return new ResponseEntity<>(user, HttpStatus.FOUND);	
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/users/findbyid")
	public ResponseEntity<Users> retriveUserById(@RequestParam int id) {
		Optional<Users> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException(MESSAGE+ID + id);
		} else {
			return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
		}
	}

	/**
	 * @param name
	 * @return
	 */
	@GetMapping("/users/findbyname")
	public ResponseEntity<List<Users>> retriveUserByName(@Valid @RequestParam String name) {
		List<Users> user = userRepository.findByName(name);
		if (user.isEmpty()) {
			throw new UserNotFoundException(MESSAGE+NAME + name);
		} else {
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		}

	}

	/**
	 * @param surname
	 * @return
	 */
	@GetMapping("/users/findbysurname")
	public ResponseEntity<List<Users>> retriveUserBySurname(@Valid @RequestParam String surname) {
		List<Users> user = userRepository.findBySurname(surname);
		if (user.isEmpty()) {
			throw new UserNotFoundException(MESSAGE+SURNAME+ surname);
		} else {
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		}

	}

	/**
	 * @param pincode
	 * @return
	 */
	@GetMapping("/users/findbypincode")
	public ResponseEntity<List<Users>> retriveUserBySurname(@RequestParam int pincode) {
		List<Users> user = userRepository.findByPincode(pincode);
		if (user.isEmpty()) {
			throw new UserNotFoundException(MESSAGE+PINCODE + pincode);
		} else {
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		}
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/usersnew")
	public ResponseEntity<Object> createUserNew(@Valid @RequestBody UsersDTO user) {
		Users cpyuser = new Users();
		cpyuser.setId(user.getId());
		cpyuser.setName(user.getName());
		cpyuser.setSurname(user.getSurname());
		cpyuser.setPincode(user.getPincode());
		cpyuser.setBirthDate(user.getBirthDate());
		cpyuser.setDateOfJoining(user.getDateOfJoining());
		cpyuser.setDeleted(user.isDeleted());
		Users createdUser = userRepository.save(cpyuser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // return /users
				.path("/{id}") 
				.buildAndExpand(createdUser.getId()) // it will set value of id
				.toUri(); // build URI /users/{id} ----> /users/4
		return ResponseEntity.created(uri).build(); // it will user URI and generate response 201 CREATED
	}
	
	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {

		Users createdUser = userRepository.save(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED); // it will user URI and generate response 201 CREATED
	}
	
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/users/harddeletebyid")
	public ResponseEntity<Object> hardDeleteUserById(@RequestParam int id) {
		Optional<Users> user = userRepository.findById(id);
		if (!user.isEmpty()) {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			throw new UserNotFoundException(MESSAGE+ID + id);
		}
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/users/softdeletebyid")
	public ResponseEntity<Object> softDeleteUserById(@RequestParam int id) {
		Optional<Users> user = userRepository.findById(id);
		if (!user.isEmpty()) {
			user.get().setDeleted(Boolean.TRUE);
			userRepository.save(user.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			throw new UserNotFoundException(MESSAGE+ID + id);
		}
	}
	
	/**
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/users/updatebyid")
	public ResponseEntity<Users> updateUserById(@PathVariable int id, @Valid @RequestBody UsersDTO user) {
		Optional<Users> checkuser = userRepository.findById(id);
		if (checkuser.isEmpty()) {
			throw new UserNotFoundException(MESSAGE+ID + id);
		} else {
			checkuser.get().setName(user.getName());
			checkuser.get().setSurname(user.getSurname());
			checkuser.get().setPincode(user.getPincode());
			checkuser.get().setBirthDate(user.getBirthDate());
			checkuser.get().setDateOfJoining(user.getDateOfJoining());
			checkuser.get().setDeleted(user.isDeleted());
			userRepository.save(checkuser.get());
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // return /users
					.path("/{id}") 
					.buildAndExpand(checkuser.get().getId()) // it will set value of id
					.toUri(); // build URI /users/{id} ----> /users/4
			return ResponseEntity.created(uri).build(); // it will user URI and generate response 201 CREATED
		}
	}

	/**
	 * @param sortby
	 * @return
	 */
	@GetMapping("/users/sortby")
	public ResponseEntity<List<Users>> retriveUserDetailsSortby(@RequestParam String sortby) {
		List<Users> user = userRepository.findAll((Sort.by(Sort.Direction.ASC, sortby)));
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}
	
}
