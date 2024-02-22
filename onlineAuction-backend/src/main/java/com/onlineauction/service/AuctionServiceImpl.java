package com.onlineauction.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Auction;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.AuctionRepository;
import com.onlineauction.util.Mapper;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
    AuctionRepository auctionRepository;
	
	@Autowired
	Mapper mapper;
	
	private Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);
	

	@Override
	public List<Auction> getEndedAuctions() throws CustomException {
	    LocalDateTime now = LocalDateTime.now();
	    System.out.println("Current time: " + now);

	    List<Auction> endedAuctions = auctionRepository.findByAuctionEndTimeBeforeAndAuctionStatus(now, "Y");
	    System.out.println("Ended auctions count: " + endedAuctions.size());
	   // endedAuctions.forEach(auction -> System.out.println("Auction ID: " + auction.getId()));
	    
	    return endedAuctions;
	}

//	public List<Auction> getEndedAuctions() throws CustomException {
//		LocalDateTime now = LocalDateTime.now();
//		
//		return auctionRepository.findByAuctionEndTimeBeforeAndAuctionStatus(now,"Y");  
//	}
//

	
	
	
	@Override
	public Auction  createOrUpdateAuction(Auction auction) throws CustomException {
		
		logger.info("Entered cretaeOrUpdateAuctions");
		ResponseStatus<Object> response = null;
		Auction auctionResponse = new Auction();		
		
		if(Objects.isNull(auction.getAuctionId())  ) {
			auction.setProductId(0);
			
		}
		
		
		auctionResponse = auctionRepository.save(auction);

		return auctionResponse;

	}

	public List<Auction> getAllActiveAuctions() throws CustomException{
		logger.info("entered getAllActiveAuctions");
		return auctionRepository.findByAuctionStatus("Y");  
		
	}
	
	
	
}
