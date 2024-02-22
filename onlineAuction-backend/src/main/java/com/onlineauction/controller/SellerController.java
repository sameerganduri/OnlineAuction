package com.onlineauction.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineauction.entity.Seller;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.SellerInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.SellerService;

@RestController
public class SellerController {

	
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/createOrUpdateSellerDetails")
	public ResponseEntity<ResponseStatus<Object>> createSeller(@RequestBody Seller seller) throws CustomException{
		
		Seller savedSeller = sellerService.createOrUpdateSeller(seller);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedSeller);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	@GetMapping("/getSellersBySellerId")
	public ResponseEntity<Object> getSellersBySellerId(@RequestParam int sellerId) throws CustomException {
		Seller savedSeller;
		savedSeller = sellerService.getSellersBySellerId(sellerId);
		
		
		if(Objects.isNull(savedSeller)) {
			throw new CustomException("Seller does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		System.out.println(savedSeller.toString());
		return new ResponseEntity<>(savedSeller,HttpStatus.OK);
	}


	@GetMapping("/getSellerByEmailId")
	public Seller getSellerByEmailId(@RequestParam String emailId) throws CustomException {
		Seller savedSeller;
		savedSeller = sellerService.getSellerByEmailId(emailId);
		
		
		if(Objects.isNull(savedSeller)) {
			throw new CustomException("Seller does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedSeller;
	}
	
	
	
	
	
}
