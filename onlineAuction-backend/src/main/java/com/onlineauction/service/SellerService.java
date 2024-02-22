package com.onlineauction.service;

import com.onlineauction.entity.Seller;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.SellerInfo;

public interface SellerService {

	Seller createOrUpdateSeller(Seller seller) throws CustomException;

	Seller getSellersBySellerId(int sellerId) throws CustomException;

	Seller getSellerByEmailId(String emailId) throws CustomException;

}
