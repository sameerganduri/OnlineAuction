package com.onlineauction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PaymentInfo {

    Integer paymentId;
    
    Integer productId;
    
    Integer customerId;
    
    String paymentMethod;
    
    String paymentStatus;


}
