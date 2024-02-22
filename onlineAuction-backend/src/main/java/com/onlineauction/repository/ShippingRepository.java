package com.onlineauction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Integer>{

	Optional<Shipping> findByProductId(int productId);

}
