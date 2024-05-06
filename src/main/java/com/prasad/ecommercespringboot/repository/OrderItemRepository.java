package com.prasad.ecommercespringboot.repository;


import com.prasad.ecommercespringboot.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
