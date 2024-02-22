package com.onlineauction.service;

import com.onlineauction.entity.Shipping;
import com.onlineauction.exception.CustomException;

public interface ShippingService {

	Shipping createOrUpdateShipping(Shipping shipping) throws CustomException;

	Shipping getShippingsByProductId(int shippingId)throws CustomException;

	
}
