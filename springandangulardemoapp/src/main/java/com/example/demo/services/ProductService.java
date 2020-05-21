package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository; 
	
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public Product updateProduct(Product product) {
		Optional<Product> productDetails=this.productRepository.findById(product.getId());
		Product productUpdate = productDetails.get();
		if(productDetails.isPresent()) {
			
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productUpdate.setDescription(product.getDescription());
			productUpdate.setPrice(product.getPrice());
			productRepository.save(productUpdate);
			return productUpdate;
		}else {
			return productUpdate;
		}
			
	}
		
	
	public long getFindId(String Productname) {
		return productRepository.findId(Productname);
	}
	
	public void updateState(long id, int currentState) {
		if(currentState == 0) {
			productRepository.updateToActive(id);
		}
		else {
			productRepository.updateToInActive(id);
		}
		
	}

	public List<Product> listOfActiveProducts(){
		return productRepository.listOfActiveProducts();
	}
	public List<Product> listOfInActiveProducts(){
		return productRepository.listOfInActiveProducts();
	}
	public boolean getStatus(String Productname) {
		boolean ProductStatus = productRepository.findProduct(Productname).isActive();
		if(ProductStatus) {
			return true;
		}else {
			return false;
		}
	}

	
}
