package com.onlineauction.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class AuctionInfo {
	 Integer auctionId;
	 Integer productId;
 	 LocalDateTime auctionStartTime;
	 LocalDateTime auctionEndTime;
	 String auctionStatus;
}
