package com.onlineauction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Product;
import com.onlineauction.entity.Seller;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.SellerRepository;
import com.onlineauction.util.Mapper;

@Service
public class SellerServiceImpl  implements SellerService{

private Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
	
	
	
	@Autowired 
	private Mapper mapper;
	
	@Autowired 
	private SellerRepository sellerRepository;
	
	
	
	
	@Override
	public Seller createOrUpdateSeller(Seller seller) throws CustomException {
		
		logger.info("Entered cretaeOrUpdateSeller");
		logger.info("Seller Id is ",seller.getSellerId().toString());
		logger.info("Seller Id is ",seller.getEmail().toString());
		
		List<Product> products = new ArrayList<>();
		
		ResponseStatus<Object> response = null;
		Seller sellerResponse = new Seller();		
		
		if(Objects.isNull(seller.getSellerId())  ) {
			seller.setSellerId(0);
			
		}
		
		
		sellerResponse = sellerRepository.save(seller);

		return seller;
	}
	
	
	
	
	
	
	@Override
	public Seller getSellersBySellerId(int sellerId) throws CustomException{
		logger.info("Entered getSellerByProdID");
		Optional<Seller> sellerOptional = sellerRepository.findBySellerId(sellerId);
		logger.info(sellerOptional.get().toString());
		if(!sellerOptional.isPresent())
		{
			throw new CustomException("Product does not exists",HttpStatus.NOT_FOUND.value());
		}
		Seller seller = sellerOptional.get();
	
		return seller;
	}



	@Override
	public Seller getSellerByEmailId(String emailId) throws CustomException {
		
		
		return sellerRepository.findByEmail(emailId);
	}
}
