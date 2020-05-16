package com.example.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u.id FROM User u WHERE u.username = :username")
	public long findId(@Param ("username") String username);
	
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.isActive=1")
	public List<User> listOfActiveUsers();
	

	@Query("SELECT u FROM User u WHERE u.isActive=0")
	public List<User> listOfInActiveUsers();
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.isActive=1 WhERE u.id = :id")
	public void updateToActive(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.isActive=0 WhERE u.id = :id")
	public void updateToInActive(@Param("id") long id);
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User findUser(@Param("username") String username);
}
