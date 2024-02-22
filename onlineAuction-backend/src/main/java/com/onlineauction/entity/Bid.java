package com.onlineauction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level=AccessLevel.PRIVATE)
@Table(name = "bids")
public class Bid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
     Integer bidId;
    
    @Column(name="product_id")
    Integer productId;
    
    @Column(name="customer_id")
    Integer customerId;
    

    @Column(name = "bid_amount", nullable = false)
     BigDecimal bidAmount;
    
    @Column(name = "bid_time", nullable = false)
     LocalDateTime bidTime;
    
    @Column(name="status")
    String status;
   

}
