package com.onlineauction.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.onlineauction.entity.Auction;
import com.onlineauction.entity.Bid;
import com.onlineauction.entity.Product;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.BidInfo;
import com.onlineauction.repository.BidRepository;
import com.onlineauction.repository.ProductRepository;
import com.onlineauction.util.Mapper;

@Service
public class BidServiceImpl implements BidService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	BidRepository bidRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired 
	ProductService productService;
	
	@Override
	public List<BidInfo> getBidsByProductId(int productId) throws CustomException {
	
		
		logger.info("Entered get Products");
		
		List<Bid> bidList=new ArrayList<>();
		try {
			bidList = bidRepository.findAllByProductIdOrderByBidAmountDesc(productId);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(),HttpStatus.NOT_FOUND.value());
		}
		
		
		return  mapper.convertToList(bidList, BidInfo.class);
		
	}

	
	
	
	

	@Override
	public BidInfo createBidOrUpdateBid(BidInfo bidInfo) {
		
		BidInfo bidInfoResp = new BidInfo();
		logger.info("In createOrUpdateBid");
		
		try {
			Product product = productService.getProductById(bidInfo.getProductId());
			
			List<Auction> auctions = product.getAuctions();
		
			Optional<Auction> activeAuction = auctions.stream().filter(auction->"Y".equalsIgnoreCase(auction.getAuctionStatus())).findFirst();
			
			LocalDateTime now = LocalDateTime.now();
			
			if(activeAuction.get().getAuctionStartTime().isBefore(now) && activeAuction.get().getAuctionEndTime().isAfter(now)) {
				List<BidInfo> existBids = getBidsByProductId(bidInfo.getProductId());
				
				BigDecimal highestBid = existBids.stream().map(BidInfo::getBidAmount).max(Comparator.naturalOrder()).orElse(product.getStartingPrice());
				
				if(bidInfo.getBidAmount().compareTo(highestBid)>0 ) {
					bidInfo.setBidTime(now);
				 bidInfoResp = mapper.convert(bidRepository.saveAndFlush(mapper.convert(bidInfo,Bid.class )),BidInfo.class);        
				}
				else {
					throw new CustomException("Please Bid greater than current highest Bid",HttpStatus.BAD_REQUEST.value());
				}
			}
			else {
				throw new CustomException("Auction not active",HttpStatus.NOT_FOUND.value());
			}
			
		} catch (CustomException e) {
			e.printStackTrace();
			
		}
		
		return bidInfoResp;
	}

	

	public List<BidInfo> getWinningBids(int customerId) throws CustomException {
		
	logger.info("Entered get winning bids");
		
		List<Bid> bidList=new ArrayList<>();
		try {
			bidList = bidRepository.findAllByCustomerIdAndStatus(customerId,"Y");
			return  mapper.convertToList(bidList, BidInfo.class);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(),HttpStatus.NOT_FOUND.value());
		}
		
		
			
	
	   
	    
	}

	
	
}
