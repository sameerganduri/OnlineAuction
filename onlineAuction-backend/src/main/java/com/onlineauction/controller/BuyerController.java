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

import com.onlineauction.entity.Customer;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.CustomerInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.BuyerService;

@RestController
public class BuyerController {

	@Autowired 
	BuyerService buyerService;
	
	
	@GetMapping("/getCustomersByCustomerId")
	public CustomerInfo getCustomerByCustomerId(@RequestParam int customerId) throws CustomException {
		CustomerInfo savedCustomer;
			savedCustomer = buyerService.getCustomerByCustomerId(customerId);
		
		
		if(Objects.isNull(savedCustomer)) {
			throw new CustomException("Customer does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedCustomer;
	}
	
	
	@GetMapping("/getCustomerByEmailId")
	public CustomerInfo getCustomerByEmailId(@RequestParam String emailId) throws CustomException {
		CustomerInfo savedCustomer;
		savedCustomer = buyerService.getCustomerByEmailId(emailId);
		
		
		if(Objects.isNull(savedCustomer)) {
			throw new CustomException("Customer does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		
		return savedCustomer;
	}
	
	
	@PostMapping("/createOrUpdateCustomerDetails")
	public ResponseEntity<ResponseStatus<Object>> createCustomer(@RequestBody Customer customer) throws CustomException{
		
		Customer savedCustomer = buyerService.createOrUpdateCustomer(customer);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedCustomer);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	
}
