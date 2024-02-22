package com.onlineauction.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerInfo {

    Integer customerId;
    
    String firstName;
    
    String lastName;
    
    String email;
    
    String password;
    
    String contactNumber;
    
    List<InvoiceInfo> invoiceInfoList;
    
    List<BidInfo> bidInfoList;
    
    List<PaymentInfo> paymentInfoList; 
    
    List<ShippingInfo> shippingInfoList;
}
