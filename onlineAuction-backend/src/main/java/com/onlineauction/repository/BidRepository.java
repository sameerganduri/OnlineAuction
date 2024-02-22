package com.onlineauction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid,Integer> {

	List<Bid> findAllByProductId(int productId);

	List<Bid> findAllByProductIdOrderByBidAmountDesc(int productId);

	List<Bid> findAllByCustomerIdAndStatus(int customerId, String status);

}
