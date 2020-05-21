package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByName(String name);

	@Query("FROM Product")
	public Product findAllProducts();
	
		
	@Query("SELECT p FROM Product p WHERE p.isActive=1")
	public List<Product> listOfActiveProducts();
	

	@Query("SELECT p FROM Product p WHERE p.isActive=0")
	public List<Product> listOfInActiveProducts();
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.isActive=1 WhERE p.id = :id")
	public void updateToActive(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.isActive=0 WhERE p.id = :id")
	public void updateToInActive(@Param("id") long id);
	
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	public Product findProduct(@Param("name") String name);


	@Query("SELECT p.id FROM Product p WHERE p.name = :name")
	public long findId(@Param ("name") String name);
}
