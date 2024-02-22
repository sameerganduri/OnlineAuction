package com.onlineauction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
     Integer invoiceId;

    @Column(name = "product_id")
    Integer productId;

    @Column(name = "seller_id")
    Integer sellerId;
    
    @Column(name = "customer_id")
    Integer customerId;
    
    
    @Column(name = "invoice_date", nullable = false)
     LocalDateTime invoiceDate;

    @Column(name = "invoice_amount", nullable = false)
     BigDecimal invoiceAmount;


}
