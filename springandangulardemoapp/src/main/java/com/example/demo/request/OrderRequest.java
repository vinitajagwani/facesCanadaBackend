package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {
	@JsonProperty(value = "id")
	private Long id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "productName")
	private String productName;

	@JsonProperty(value = "phone_number")
	private Long phoneNumber;

	@JsonProperty(value = "address")
	private String address;


	@JsonProperty(value = "pincode")
	private Long pincode;


	@JsonProperty(value = "isActive")
	private boolean isActive;

	@JsonProperty(value = "isApproved")
	private boolean isApproved;

	
	public OrderRequest(Long id, String name, String productName, Long phoneNumber, String address, Long pincode,
			boolean isActive, boolean isApproved) {
		super();
		this.id = id;
		this.name = name;
		this.productName = productName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.pincode = pincode;
		this.isActive = isActive;
		this.isApproved = isApproved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}
