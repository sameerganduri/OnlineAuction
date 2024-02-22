package com.onlineauction.entity;

import java.util.Date;

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
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level=AccessLevel.PRIVATE)
@Table(name = "shipping")
public class Shipping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
     Integer shippingId;
    
    @Column(name="product_id")
    Integer productId;
    
    @Column(name="customer_id")
    Integer customerId;
     
    @Column(name = "shipping_address", nullable = false)
     String shippingAddress;
    
    @Column(name = "shipping_method", nullable = false)
     String shippingMethod;
    

}
