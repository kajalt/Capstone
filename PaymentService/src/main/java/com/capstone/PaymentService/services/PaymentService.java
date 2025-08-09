package com.capstone.PaymentService.services;

import com.capstone.PaymentService.gateway.BasePaymentGateway;
import  com.capstone.PaymentService.gateway.PaymentGatewayStrategyChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayStrategyChooser paymentGatewayStrategyChooser;

    public String initiatePayment(Long amount, String phoneNumber, String name, String email, String orderId) {
        BasePaymentGateway paymentGateway = paymentGatewayStrategyChooser.getBestPaymentGateway();
        return paymentGateway.generatePaymentLink(amount,phoneNumber,name,email,orderId);
    }
}