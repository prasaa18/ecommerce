package com.prasad.ecommercespringboot.repository;

import com.prasad.ecommercespringboot.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address ,Long> {
}
