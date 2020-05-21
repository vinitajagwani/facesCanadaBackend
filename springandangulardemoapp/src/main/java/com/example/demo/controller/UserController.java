package com.example.demo.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.MessageResponse;
import com.example.demo.services.UserDetailsServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@GetMapping("/user/list")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUser() {
		return (List<User>) userRepository.findAll();
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

	@PutMapping("/update/user/{id}")
	public ResponseEntity<User> getUpdatedUser(@PathVariable long id, @RequestBody User user) {
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
	@PutMapping("/editProfile/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public ResponseEntity<?> updateUserInformation(@PathVariable("id") Long id,
			  										@RequestBody User userInformation) {
		
	    Optional<User> userData = userRepository.findById(id);
	    User user = userData.get();
	    
	    boolean isExistsUsername = userRepository.existsByUsername(userInformation.getUsername());
	    boolean isExistsEmail = userRepository.existsByEmail(userInformation.getEmail());
	   
	    if (userData.isPresent()) {
	    	
	    	if(user.getUsername().endsWith(userInformation.getUsername()))
	    	{
	    		if(isExistsEmail) {
		    		return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Email is already taken!"));
		    	}
	    		else {
	    			user.setEmail(userInformation.getEmail());
	    		}
	    	}
	    	
	    	else if(user.getEmail().endsWith(userInformation.getEmail()))
	    	{
	    		if(isExistsUsername) {
		    		return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Username is already taken!"));
		    	}
	    		else {
	    			user.setUsername(userInformation.getUsername());
	    		}
	    	}
	    	else {
	    		user.setUsername(userInformation.getUsername());
	    	    user.setEmail(userInformation.getEmail());
	    	}
	    
	      return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
	    }
	    else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PutMapping("/changePassword/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> changePassword(@PathVariable("id") Long id,
											@RequestBody User userInformation){
		
		Optional<User> userData = userRepository.findById(id);
		
		if (userData.isPresent()) {
	    	
	    User user = userData.get();
	    
	    user.setPassword(encoder.encode(userInformation.getPassword()));
	    
	      return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
	    }
	    else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
}