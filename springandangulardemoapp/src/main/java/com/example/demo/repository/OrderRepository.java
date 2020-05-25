package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;
import com.example.demo.request.OrderRequest;
import com.example.demo.request.OrdersPerUserRequest;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//	@Query("SELECT count(id) AS NUMBER FROM users INNER JOIN orders AS o Using(id) GROUP BY (o.user_id) ORDER BY Number DESC LIMIT 5")
//	public List<Long> orderPerUser();
	@Query("SELECT new com.example.demo.request.OrdersPerUserRequest(u.username, COUNT(*) AS number_of_orders)  FROM Order o JOIN User u on o.userId=u.id GROUP BY u.username ORDER BY u.id")
	public List<OrdersPerUserRequest> orderPerUser();
	@Query("SELECT new com.example.demo.request.OrderRequest(o.id,o.name,p.name,o.phoneNumber,o.address,o.pincode,o.isActive,o.isApproved) FROM Order o JOIN User u on o.userId=u.id JOIN Product p on o.productId=p.id WHERE o.userId = :id")
	public List<OrderRequest> orderDetailById(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Order o SET o.isActive = 1, o.isApproved = 1 WHERE o.id = :id")
	public void isActive(@Param("id") long id );
	
	@Transactional
	@Modifying
	@Query("UPDATE Order o SET o.isActive = 0, o.isApproved = 0 WHERE o.id = :id")
	public void isInActive(@Param("id") long id );
	
	@Query("SELECT o FROM Order o WHERE o.isApproved = 1")
	public List<Order> findApprovedOrder();

	@Query("SELECT o FROM Order o WHERE o.isApproved = 0")
	public List<Order> findRejectedOrder();

	@Query("FROM Order o WHERE o.isApproved = 0")
	public List<Long> existsRejectedOrder();
	
	@Query("FROM Order o WHERE o.isApproved = 1")
	public int existsApprovedOrder();
	
	
}
