package com.onlineauction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

	Optional<Payment> findByProductId(int productId);

	
	
}
