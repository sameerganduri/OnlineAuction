package com.onlineauction.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ShippingInfo {
	Integer shippingId;
	
	
    String trackingNumber;
    String shippingAddress;
    String shippingMethod;
    
    Integer productId;
    Integer customerId;
}
