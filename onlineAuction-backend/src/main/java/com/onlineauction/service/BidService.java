package com.onlineauction.service;

import java.util.List;

import com.onlineauction.exception.CustomException;
import com.onlineauction.model.BidInfo;

public interface BidService {

	List<BidInfo> getBidsByProductId(int productId) throws CustomException;




	BidInfo createBidOrUpdateBid(BidInfo bidInfo);

	public List<BidInfo> getWinningBids(int customerId) throws CustomException;
}
