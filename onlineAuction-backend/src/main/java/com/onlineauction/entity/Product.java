package com.onlineauction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
@FieldDefaults(level=AccessLevel.PRIVATE)
//@JsonIgnoreProperties({"auctions", "bids"})
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
     Integer productId;

    @Column(name = "product_name")
     String productName;

    @Column(name = "description")
     String description;

    @Column(name = "starting_price")
     BigDecimal startingPrice;

    @Column(name = "image_url")
     String imageUrl;
    
    @Column(name="seller_id")
    Integer sellerId;
    
    @Column(name="category_id")
    Integer categoryId;

  @OneToMany(targetEntity = Auction.class, cascade = CascadeType.ALL)
  @JoinColumn(name="product_id",referencedColumnName="product_id")
  List<Auction> auctions = new ArrayList<>();

  @OneToMany(targetEntity = Bid.class, cascade = CascadeType.ALL)
  @JoinColumn(name="product_id",referencedColumnName="product_id")
  List<Bid> bids = new ArrayList<>();
    
  @OneToOne(targetEntity = Payment.class)
  @JoinColumn(name="product_id",referencedColumnName="product_id")
  Payment payment;
//  
  @OneToMany(targetEntity = Invoice.class, cascade = CascadeType.ALL)
  @JoinColumn(name="product_id",referencedColumnName="product_id")
  List<Invoice> invoices = new ArrayList<>(); 
//    
  @OneToOne(targetEntity = Shipping.class)
  @JoinColumn(name="product_id",referencedColumnName="product_id")
  Shipping shipping;
}
