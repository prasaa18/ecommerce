package com.prasad.ecommercespringboot.repository;

import com.prasad.ecommercespringboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
