package com.example.demo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductRequest {
	@NotBlank
    @Size(min = 3, max = 50)
    private String name;
	
	@NotBlank
	private String image;
    
	private int price;
    
    @NotBlank
    @Size(min = 6, max = 200)
    private String description;
    
   
  
    private boolean isActive;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
}
