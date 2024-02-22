package com.onlineauction.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineauction.exception.CustomException;
import com.onlineauction.model.BidInfo;
import com.onlineauction.model.ProductInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.BidService;

@RestController
public class BiddingController {

	@Autowired 
	BidService bidService;
	
	
	
	
	@PostMapping("/createOrUpdateBid")
	public ResponseEntity<ResponseStatus<Object>> createBidOrUpdateBid(@RequestBody BidInfo bidInfo)
	{
			BidInfo bidResponse = bidService.createBidOrUpdateBid(bidInfo);
			
			if(Objects.isNull(bidResponse.getBidId())  ) {
				ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"failure",bidResponse);
				
				return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.BAD_REQUEST);
			}
			
			ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",bidResponse);
			
			
			
			return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
			
			
		
		
		
	}
	
	@GetMapping("/getBidsByProductId")
	public List<BidInfo> getBidsByProductId(@RequestParam ("productId") int productId) throws CustomException {
		List<BidInfo> savedBid;
		savedBid = bidService.getBidsByProductId(productId);
		
		
		if(Objects.isNull(savedBid)) {
			throw new CustomException("Product does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedBid;
	}
	
	@GetMapping("/getWinBids")
	public List<BidInfo> getWinBidsByCustomerId(@RequestParam ("customerId") int customerId) throws CustomException {
		List<BidInfo> savedBid;
		savedBid = bidService.getWinningBids(customerId);
		
		if(Objects.isNull(savedBid)) {
			throw new CustomException("Product does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedBid;
	}
	
	
	
}
