package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/signup")
	public String signupUser(@RequestBody User user)
	{	
			boolean checkFistName = UserValidation.isValidName(user.getFirstName());
			boolean checkLastName = UserValidation.isValidName(user.getLastName());
			boolean checkEmail = UserValidation.isValidEmail(user.getEmail());
			boolean checkPassword = UserValidation.isValidPassword(user.getPassword());
			
			if(checkFistName && checkLastName && checkEmail && checkPassword) {
				System.out.println("Signup Successful");
				userRepository.save(user);
				return "SignUp Successful";
			}
			else
			{
				System.out.println("Signup fail");
				return "Signup fail";
			}
	}
}
