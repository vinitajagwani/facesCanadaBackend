package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersPerUserRequest {

	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "orders")
	private long orders;

	public OrdersPerUserRequest(String username, long orders) {
		super();
		this.username = username;
		this.orders = orders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getOrders() {
		return orders;
	}

	public void setOrders(long orders) {
		this.orders = orders;
	}
	
	
}
