package com.onlineauction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer>{

	Customer findByEmail(String email);
}
