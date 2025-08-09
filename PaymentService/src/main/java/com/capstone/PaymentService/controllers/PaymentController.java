package com.capstone.PaymentService.controllers;

import com.capstone.PaymentService.dtos.InitiatePaymentRequestDto;
import com.capstone.PaymentService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequestDto) {
        return paymentService.initiatePayment(initiatePaymentRequestDto.getAmount(), initiatePaymentRequestDto.getPhoneNumber(), initiatePaymentRequestDto.getName(), initiatePaymentRequestDto.getEmail(), initiatePaymentRequestDto.getOrderId());
    }
}