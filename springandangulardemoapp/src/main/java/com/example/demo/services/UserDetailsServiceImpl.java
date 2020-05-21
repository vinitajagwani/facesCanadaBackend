package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);

	}
	
	public User updateUser(User user) {
		Optional<User> userDetail=this.userRepository.findById(user.getId());
		if(userDetail.isPresent()) {
			User userUpdate = userDetail.get();
			userUpdate.setId(user.getId());
			userUpdate.setUsername(user.getUsername());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPassword(bcryptEncoder.encode(user.getPassword()));
			userRepository.save(userUpdate);
			return userUpdate;
		}else {
			return user;
		}
			
	}
	public long getFindId(String username) {
		return userRepository.findId(username);
	}
	
	public void updateState(long id, int currentState) {
		if(currentState == 0) {
			userRepository.updateToActive(id);
		}
		else {
			userRepository.updateToInActive(id);
		}
		
	}

	public List<User> listOfActiveUsers(){
		return userRepository.listOfActiveUsers();
	}
	public List<User> listOfInActiveUsers(){
		return userRepository.listOfInActiveUsers();
	}
	public boolean getStatus(String username) {
		boolean userStatus = userRepository.findUser(username).isActive();
		if(userStatus) {
			return true;
		}else {
			return false;
		}
	}

}
