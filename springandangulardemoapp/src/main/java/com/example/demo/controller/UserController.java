package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserDetailsServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@GetMapping("/user/list")
	public List<User> getAllUser() {
		return (List<User>) userService.getAllUsers();
	}

//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/user/single/{id}")
	public Optional<User> getSingleUser(@PathVariable Long id) {
		return userRepository.findById(id);
	}
	@GetMapping("/user/{username}")
	public long getFindIdByname(@PathVariable String username) {
		return userService.getFindId(username);
	}
	@GetMapping("/user/updatestatus/{id}/{currentState}")
	public void changeStatus(@PathVariable long id, @PathVariable int currentState) {
		userService.updateState(id, currentState);
	}
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/user/delete/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	

	@PutMapping("/update/user/{id}")
	public ResponseEntity<User> getUpdateUser(@PathVariable long id, @RequestBody User user) {
		user.setId(id);
		return ResponseEntity.ok().body(this.userService.updateUser(user));
	}
	
	@GetMapping("/user/active")
	public List<User> getActiveUsers(){
		return userService.listOfActiveUsers();
	}
	
	@GetMapping("/user/inactive")
	public List<User> getInActiveUsers(){
		return userService.listOfInActiveUsers();
	}
	
}