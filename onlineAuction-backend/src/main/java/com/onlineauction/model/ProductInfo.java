package com.onlineauction.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductInfo {

    Integer productId;
    
    String productName;
    
    String description;
    
    Double startingPrice;
    
    String imageURL;
    
    Integer sellerId;
 
    Integer categoryId;

  List<AuctionInfo> auctions = new ArrayList<>();

  List<BidInfo> bids = new ArrayList<>();
    
   PaymentInfo payment;

   List<InvoiceInfo> invoices = new ArrayList<>(); 

   ShippingInfo shipping;
    
}
