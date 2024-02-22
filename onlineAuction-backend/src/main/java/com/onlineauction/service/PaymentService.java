package com.onlineauction.service;

import com.onlineauction.entity.Payment;
import com.onlineauction.exception.CustomException;

public interface PaymentService {

	Payment createOrUpdatePayment(Payment payment) throws CustomException;

	Payment getPaymentsByProductId(int productId) throws CustomException;

}
