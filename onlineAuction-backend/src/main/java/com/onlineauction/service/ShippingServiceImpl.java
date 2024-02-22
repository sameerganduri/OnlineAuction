package com.onlineauction.service;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Shipping;
import com.onlineauction.entity.Shipping;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.ShippingRepository;
import com.onlineauction.util.Mapper;

@Service
public class ShippingServiceImpl implements ShippingService{

	private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	
	
	@Autowired 
	private Mapper mapper;
	
	@Autowired 
	private ShippingRepository shippingRepository;
	
	
	@Override
	public Shipping createOrUpdateShipping(Shipping shipping) throws CustomException {
		
		logger.info("Entered cretaeOrUpdateShipping");
		ResponseStatus<Object> response = null;
		Shipping shippingResponse = new Shipping();		
		
		if(Objects.isNull(shipping.getProductId())  ) {
			shipping.setShippingId(0);
			
		}
		
		
		shippingResponse = shippingRepository.saveAndFlush(shipping);

		return shipping;
	}
	
	
	
	@Override
	public Shipping getShippingsByProductId(int productId) throws CustomException{
		logger.info("Entered getShippingByProdID");
		Optional<Shipping> shippingOptional = shippingRepository.findByProductId(productId);
		logger.info(shippingOptional.get().toString());
		if(!shippingOptional.isPresent())
		{
			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
		}
		Shipping shipping = shippingOptional.get();
	
		return shipping;
	}
}
