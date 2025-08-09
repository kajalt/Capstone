package com.capstone.PaymentService.controllers;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class WebhookController {

    //https://stackoverflow.com/questions/21197622/how-to-receive-webhook-from-stripe-in-java
    @PostMapping
    public void receiveWebhookEvents(@RequestBody String stripeEvent) {
        System.out.println(stripeEvent);
    }
}