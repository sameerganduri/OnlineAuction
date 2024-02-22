package com.onlineauction.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerInfo {
	 Integer sellerId;
     String firstName;
     String lastName;
     String email;
     String password;
     List<ProductInfo> products;
     Double sellerRating;
}
