package com.onlineauction.service;

import java.util.List;

import com.onlineauction.entity.Auction;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.AuctionInfo;

public interface AuctionService {

	//write a service to make auction status N
	
	//if status is N get those auctions
	
	public List<Auction> getEndedAuctions() throws CustomException;

	public Auction createOrUpdateAuction(Auction auction) throws CustomException;

	public List<Auction> getAllActiveAuctions() throws CustomException;
	
	
	
}
