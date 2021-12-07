package com.mypoc.userdetailtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypoc.userdetailtracker.user.bean.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	List<Users> findByName(String name);
	List<Users> findBySurname(String surname);
	List<Users> findByPincode(int pincode);
	Users deleteByPincode(int id);
}