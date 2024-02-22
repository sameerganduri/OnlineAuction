package com.onlineauction.entity;

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
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
     Integer auctionId;
    
    @Column(name="product_id")
    Integer productId;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//     Product product;

    @Column(name = "auction_start_time", nullable = false)
     LocalDateTime auctionStartTime;

    @Column(name = "auction_end_time", nullable = false)
     LocalDateTime auctionEndTime;

    @Column(name = "auction_status", nullable = false)
     String auctionStatus;


    
    
}

