package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table( name="products",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "productname") 
	})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String productName;

	@NotBlank
	private String productImage;
	
	@NotBlank
	private int price;

	@NotBlank
	@Size(max = 200)
	private String description;
	
	private boolean isActive;

	public Product() {

	}

	
	public Product(String productName, String productImage,
		int price, String description, boolean isActive) {
		
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.description = description;
		this.isActive = isActive;
	}


	public Long getProduct_id() {
		return id;
	}

	public void setProduct_id(Long product_id) {
		this.id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
