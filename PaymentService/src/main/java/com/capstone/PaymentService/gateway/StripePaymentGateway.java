package com.capstone.PaymentService.gateway;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements BasePaymentGateway {
    @Override
    public String generatePaymentLink(Long amount, String phoneNumber, String name, String email, String orderId) {
        return null;
    }
}