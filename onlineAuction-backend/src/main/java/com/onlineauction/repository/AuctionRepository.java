package com.onlineauction.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.Auction;
import com.onlineauction.model.AuctionInfo;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Integer> {

	List<Auction> findByAuctionEndTimeBeforeAndAuctionStatus(LocalDateTime now, String string);

	List<Auction> findByAuctionStatus(String status);

	List<Auction> findByAuctionEndTimeAfterAndAuctionStatus(LocalDateTime now, String string);

	
	
}
