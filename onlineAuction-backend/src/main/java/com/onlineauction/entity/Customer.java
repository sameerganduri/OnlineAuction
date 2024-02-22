package com.onlineauction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
     Integer customerId;
    
    @Column(name = "first_name", nullable = false)
     String firstName;
    
    @Column(name = "last_name", nullable = false)
     String lastName;
    
    @Column(name = "email_id", nullable = false, unique = true)
     String email;
    
    @Column(name = "password", nullable = false)
     String password;
    
    @OneToMany(targetEntity=Bid.class,cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id",updatable = false)
    List<Bid> bidlist;
    
  
    @OneToMany(targetEntity=Invoice.class,cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id",updatable = false)
    List<Invoice> invoices; 
    
    @OneToMany(targetEntity=Payment.class,cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id",updatable = false)
    List<Payment> payments;
    
    @OneToMany(targetEntity=Shipping.class,cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "customer_id",updatable = false)
    List<Shipping> shippings;
  }
