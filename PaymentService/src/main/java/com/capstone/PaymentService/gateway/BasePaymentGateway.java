package com.capstone.PaymentService.gateway;

public interface BasePaymentGateway {
    String generatePaymentLink(Long amount, String phoneNumber, String name, String email, String orderId);
}