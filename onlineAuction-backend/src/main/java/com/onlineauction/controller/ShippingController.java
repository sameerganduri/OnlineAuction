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

import com.onlineauction.entity.Shipping;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.ShippingService;

@RestController
public class ShippingController {

	@Autowired
	private ShippingService shippingService;
	
	@PostMapping("/createOrUpdateShippingDetails")
	public ResponseEntity<ResponseStatus<Object>> createShipping(@RequestBody Shipping shipping) throws CustomException{
		
		Shipping savedShipping = shippingService.createOrUpdateShipping(shipping);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedShipping);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	@GetMapping("/getShippingsByShippingId")
	public ResponseEntity<Object> getShippingsByShippingId(@RequestParam int shippingId) throws CustomException {
		Shipping savedShipping;
		savedShipping = shippingService.getShippingsByProductId(shippingId);
		
		
		if(Objects.isNull(savedShipping)) {
			throw new CustomException("Shipping does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		System.out.println(savedShipping.toString());
		return new ResponseEntity<>(savedShipping,HttpStatus.OK);
	}


	
	
}
