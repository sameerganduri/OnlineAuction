package com.onlineauction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
public class BidInfo {

    Integer bidId;
    
    Integer productId;
    
    Integer customerId;
    
    BigDecimal bidAmount;
    
    LocalDateTime bidTime;
    
    
    String status;
}
