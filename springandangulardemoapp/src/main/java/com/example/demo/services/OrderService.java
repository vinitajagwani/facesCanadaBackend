package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.request.OrderDTO;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderDTO> getAllDetailOfUser(Long id){
		List<OrderDTO> serviceDetail = orderRepository.orderDetailById(id);
		return serviceDetail;
	}
	
	public void changeStatus(long id, int number) {
		if(number==0) {
			 orderRepository.isActive(id);
		}else {
			orderRepository.isInActive(id);
		}
	}
	
	public boolean checkRejectedList() {
		List<Long> rejectedOrder = orderRepository.existsRejectedOrder();
		if(rejectedOrder.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkApprovedList() {
		int approvedOrder = orderRepository.existsApprovedOrder();
		if(approvedOrder>0) {
			return false;
		}else {
			return true;
		}
	}
	
	public Order updateOrderDetail(Order order) {
		Optional<Order> orderDetail = this.orderRepository.findById(order.getId());
		Order orderUpdate = orderDetail.get();
		if (orderDetail.isPresent()) {
			
			orderUpdate.setId(order.getId());
			orderUpdate.setName(order.getName());
			orderUpdate.setAddress(order.getAddress());
			orderUpdate.setPincode(order.getPincode());
			orderUpdate.setPhoneNumber(order.getPhoneNumber());
			orderRepository.save(orderUpdate);
			return orderUpdate;
		} else {
			return orderUpdate;
		}

	}
}
