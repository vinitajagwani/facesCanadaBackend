package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserData;
import com.example.demo.repository.UserRepository;
import com.example.demo.validation.UserValidation;


@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/signup")
	public UserData signupUser(@RequestBody UserData userInformation)
	{	
//			boolean checkFistName = UserValidation.isValidName(userInformation.getFirstName());
//			boolean checkLastName =UserValidation.isValidName(userInformation.getLastName());
//			boolean checkEmail = UserValidation.isValidEmail(userInformation.getEmail());
//			boolean checkPassword = UserValidation.isValidPassword(userInformation.getPassword());
//			
//			if(checkFistName && checkLastName && checkEmail && checkPassword) {
//				System.out.println("Signup Successful");
				return userRepository.save(userInformation);
//				return "SignUp Successful";
			}
}
//			else
//			{
//				System.out.println("Signup fail");
//				return "Signup fail";
//			}