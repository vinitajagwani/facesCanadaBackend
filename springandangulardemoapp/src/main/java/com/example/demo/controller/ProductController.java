package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.ProductRequest;
import com.example.demo.response.MessageResponse;
import com.example.demo.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;

	@PostMapping("/newProduct")
	public ResponseEntity<?> registerProduct(@Valid @RequestBody ProductRequest productRequest) {
		
		Product product = new Product(productRequest.getName(),
				productRequest.getImage(),
				productRequest.getPrice(),
				productRequest.getDescription(),
				productRequest.isActive());

		product.setActive(true);
		productRepository.save(product);

		return ResponseEntity.ok(new MessageResponse("Product added successfully!"));
	}
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

	@GetMapping("/updatestatus/{id}/{currentState}")
	public void changeStatus(@PathVariable long id, @PathVariable int currentState) {
		productService.updateState(id, currentState);
	}
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable long id) {
		productRepository.deleteById(id);
	}
	
	@GetMapping("/active")
	public List<Product> getActiveProduct(){
		return productService.listOfActiveProducts();
	}
	
	@GetMapping("/inactive")
	public List<Product> getInActiveUsers(){
		return productService.listOfInActiveProducts();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> getUpdateProduct(@PathVariable long id, @RequestBody Product product) {
		product.setId(id);
		return ResponseEntity.ok().body(this.productService.updateProduct(product));
	}
	}
