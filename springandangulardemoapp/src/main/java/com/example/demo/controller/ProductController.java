package com.example.demo.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.ProductRequest;
import com.example.demo.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@PostMapping("/newProduct")
	public ResponseEntity<?> registerUser(@Valid @RequestBody ProductRequest productRequest) {
		

		// Create new user's account
		Product product = new Product(productRequest.getProductName(), 
				productRequest.getProductImage(),
				productRequest.getPrice(),
				productRequest.getDescription(),
							 productRequest.isActive());

		product.setActive(true);
		productRepository.save(product);

		return ResponseEntity.ok(new MessageResponse("Product added successfully!"));
	}

}
