package com.onlineauction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

	List<Product> findByCategoryId(int categoryId);


	    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	    List<Product> searchProductsByProductNameOrDescription(@Param("searchTerm") String searchTerm);


		List<Product> findBySellerId(int sellerId);

	}

