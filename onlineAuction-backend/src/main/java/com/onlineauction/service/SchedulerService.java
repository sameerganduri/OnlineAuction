package com.onlineauction.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.onlineauction.entity.Auction;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.AuctionInfo;
import com.onlineauction.model.BidInfo;
import com.onlineauction.model.CustomerInfo;
import com.onlineauction.repository.AuctionRepository;
import com.onlineauction.util.Mapper;

@Component
public class SchedulerService {

	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private AuctionRepository auctionRepository;
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Mapper mapper;
	
	private Logger logger = LoggerFactory.getLogger(SchedulerService.class);
	
	@Scheduled(fixedDelay=6000)
	public void checkAuctionWinners() {
		
		try {
	//	logger.info("Scheduler working");
			List<Auction> endedAuctions = auctionService.getEndedAuctions();
			List<Auction> auctions = auctionService.getAllActiveAuctions();
		//	logger.info(endedAuctions.toString());
			for(Auction auction : endedAuctions) {
				auction.setAuctionStatus("N");
				auctionService.createOrUpdateAuction(auction);
				List<BidInfo> bids = bidService.getBidsByProductId(auction.getProductId());
				
				if(Objects.nonNull(bids)) {
					BidInfo highestBid = bids.get(0);
					CustomerInfo winner = buyerService.getCustomerByCustomerId(highestBid.getCustomerId());
					
					if(Objects.nonNull(winner)) {
						//Redirect to payments and make payment
						//notify winner
						highestBid.setStatus("Y");		
						
						bidService.createBidOrUpdateBid(highestBid);
						
					
					}
					
				}
				
				
			
			}
		
		
		
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}

