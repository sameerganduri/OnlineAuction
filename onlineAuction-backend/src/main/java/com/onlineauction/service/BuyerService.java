package com.onlineauction.service;

import com.onlineauction.entity.Customer;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.CustomerInfo;

public interface BuyerService {

	CustomerInfo getCustomerByCustomerId(int customerId) throws CustomException;

	Customer createOrUpdateCustomer(Customer customer) throws CustomException;

	CustomerInfo getCustomerByEmailId(String emailId) throws CustomException;

}
