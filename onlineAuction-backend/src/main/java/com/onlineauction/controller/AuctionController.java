package com.onlineauction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineauction.entity.Auction;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.AuctionService;

@RestController
public class AuctionController {

	@Autowired
	AuctionService auctionService;
	
	@PostMapping("/createOrUpdateAuctionDetails")
	public ResponseEntity<ResponseStatus<Object>> createAuction(@RequestBody Auction auction) throws CustomException{
		
		Auction savedAuction = auctionService.createOrUpdateAuction(auction);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedAuction);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getActiveAuctions")
	public List<Auction> getAllActiveAuctions() throws CustomException
	{
		List<Auction> activeAuctions = auctionService.getAllActiveAuctions();
		return activeAuctions;
	}
}
