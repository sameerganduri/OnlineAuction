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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "sellers")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
     Integer sellerId;
    
    @Column(name = "first_name")
     String firstName;
    
    @Column(name = "last_name")
     String lastName;
    
    @Column(name = "email", unique = true, nullable = false)
     String email;
    
    @Column(name = "password", nullable = false)
     String password;
    
    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name="seller_id",referencedColumnName = "seller_id",updatable = false) 
    List<Product> products;
    
    
    
    @Column(name = "seller_rating")
     Double sellerRating;
    
    
}
