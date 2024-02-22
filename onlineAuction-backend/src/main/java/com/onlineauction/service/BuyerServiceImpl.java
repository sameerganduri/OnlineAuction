package com.onlineauction.service;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Customer;
import com.onlineauction.entity.Customer;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.CustomerInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.CustomerRepository;
import com.onlineauction.util.Mapper;

@Service
public class BuyerServiceImpl implements BuyerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Mapper mapper;
	
	private Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);

	
	@Override
	public CustomerInfo getCustomerByCustomerId(int customerId) throws CustomException {
	//	logger.info("Entered getProductById");
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
	//	logger.info(productOptional.get().toString());
		if(!customerOptional.isPresent())
		{
			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
		}
		Customer customer = customerOptional.get();
		CustomerInfo customerInfo = mapper.convert(customer, CustomerInfo.class);
		return customerInfo;
	}
	
	
	@Override
	public Customer createOrUpdateCustomer(Customer customer) throws CustomException {
		
		logger.info("Entered cretaeOrUpdateCustomer");
		ResponseStatus<Object> response = null;
		Customer customerResponse = new Customer();		
		
		if(Objects.isNull(customer.getCustomerId())  ) {
			customer.setCustomerId(0);
			
		}
		
		
		customerResponse = customerRepository.saveAndFlush(customer);

		return customer;
	}


	@Override
	public CustomerInfo getCustomerByEmailId(String emailId) throws CustomException {
//		logger.info("Entered getProductById");
			Customer customerOptional = customerRepository.findByEmail(emailId);
			CustomerInfo customerInfo = mapper.convert(customerOptional, CustomerInfo.class);
			return customerInfo;
	}
	
}
