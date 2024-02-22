package com.onlineauction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level=AccessLevel.PRIVATE)
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
     Integer paymentId;

    @Column(name="product_id")
    Integer productId;
    
    @Column(name="customer_id")
    Integer customerId;


    @Column(name = "payment_method", nullable = false)
     String paymentMethod;

    @Column(name = "payment_status", nullable = false)
     String paymentStatus;

}
