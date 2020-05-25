package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.request.OrderRequest;
import com.example.demo.request.OrdersPerUserRequest;
import com.example.demo.services.OrderService;


@RequestMapping("api/order/")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {


	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@GetMapping("list")
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Order> getSingleOrder(@PathVariable long id) {
		return orderRepository.findById(id);
	}
	
	@GetMapping("ordersPerUser")
	public List<OrdersPerUserRequest> orderPerUser(){
		return orderRepository.orderPerUser();
	}

	@PostMapping("placeOrder")
	public Order saveOrder(@RequestBody Order order) {
		return orderRepository.save(order);
	}

	@GetMapping("myorder/{id}")
	public List<OrderRequest> getAllDetailOfUser(@PathVariable long id) {
		return orderService.getAllDetailOfUser(id);

	}

	@GetMapping("statuschange/{id}/{number}")
	public void changeStatus(@PathVariable long id, @PathVariable int number) {

		orderService.changeStatus(id, number);
	}

	@GetMapping("approved/list")
	public List<Order> getApprovedOrder() {
			return orderRepository.findApprovedOrder();

	}

	@GetMapping("rejected/list")
	public List<Order> getRejectedOrder(boolean rejectedOrder) {
		return	orderRepository.findRejectedOrder();

	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Order> getUpdateOrder(@PathVariable long id, @RequestBody Order order) {
		order.setId(id);
		return ResponseEntity.ok().body(this.orderService.updateOrderDetail(order));
	}

	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable long id) {
		orderRepository.deleteById(id);
	}
}
