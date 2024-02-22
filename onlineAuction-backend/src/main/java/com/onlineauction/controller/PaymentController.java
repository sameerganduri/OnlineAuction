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

import com.onlineauction.entity.Payment;
import com.onlineauction.entity.Payment;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/createOrUpdatePaymentDetails")
	public ResponseEntity<ResponseStatus<Object>> createPayment(@RequestBody Payment payment) throws CustomException{
		
		Payment savedPayment = paymentService.createOrUpdatePayment(payment);
		ResponseStatus<Object> responseObj = new ResponseStatus<>(HttpStatus.ACCEPTED.value(),"success",savedPayment);
		return new ResponseEntity<ResponseStatus<Object>>(responseObj,HttpStatus.OK);
		
	}
	
	@GetMapping("/getPaymentsByPaymentId")
	public ResponseEntity<Object> getPaymentsByPaymentId(@RequestParam int productId) throws CustomException {
		Payment savedPayment;
		savedPayment = paymentService.getPaymentsByProductId(productId);
		
		
		if(Objects.isNull(savedPayment)) {
			throw new CustomException("Payment does not exist with ID",HttpStatus.NOT_FOUND.value());
			
		}
		System.out.println(savedPayment.toString());
		return new ResponseEntity<>(savedPayment,HttpStatus.OK);
	}



}

