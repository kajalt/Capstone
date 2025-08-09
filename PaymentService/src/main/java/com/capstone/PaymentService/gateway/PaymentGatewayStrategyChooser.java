package com.capstone.PaymentService.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayStrategyChooser {

    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public BasePaymentGateway getBestPaymentGateway() {
        return razorpayPaymentGateway;
    }
}