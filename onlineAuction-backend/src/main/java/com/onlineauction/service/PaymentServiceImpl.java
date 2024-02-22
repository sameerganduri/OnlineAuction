package com.onlineauction.service;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Payment;
import com.onlineauction.entity.Product;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.PaymentRepository;
import com.onlineauction.util.Mapper;

@Service
public class PaymentServiceImpl implements PaymentService {

private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public Payment createOrUpdatePayment(Payment payment) throws CustomException {
		
		logger.info("Entered cretaeOrUpdatePayment");
		ResponseStatus<Object> response = null;
		Payment paymentResponse = new Payment();		
		
		if(Objects.isNull(payment.getProductId())  ) {
			payment.setPaymentId(0);
			
		}
		
		
		paymentResponse = paymentRepository.saveAndFlush(payment);

		return payment;
	}
	
	
	
	@Override
	public Payment getPaymentsByProductId(int productId) throws CustomException{
		logger.info("Entered getPaymentByProdID");
		Optional<Payment> paymentOptional = paymentRepository.findByProductId(productId);
		logger.info(paymentOptional.get().toString());
		if(!paymentOptional.isPresent())
		{
			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
		}
		Payment payment = paymentOptional.get();
	
		return payment;
	}
}
