package com.capstone.PaymentService.gateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements BasePaymentGateway {
    @Value("${stripe.apikey}")
    private String apiKey;

    @Override
    public String generatePaymentLink(Long amount, String phoneNumber, String name, String email, String orderId) {
        try {
            Stripe.apiKey = this.apiKey;
            Price price = getPrice(amount);
            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder().addLineItem(PaymentLinkCreateParams.LineItem.builder().setPrice(price.getId()).setQuantity(1L).build()).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder().setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT).setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl("https://scaler.com").build()).build())

                    .build();
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (StripeException ex) {
            throw new RuntimeException(String.valueOf(ex));
        }
    }

    private Price getPrice(Long amount) {
        try {
            PriceCreateParams params = PriceCreateParams.builder().setCurrency("usd").setUnitAmount(amount).setRecurring(PriceCreateParams.Recurring.builder().setInterval(PriceCreateParams.Recurring.Interval.MONTH).build()).setProductData(PriceCreateParams.ProductData.builder().setName("Gold Plan").build()).build();
            return Price.create(params);
        } catch (StripeException ex) {
            throw new RuntimeException(String.valueOf(ex));
        }
    }

}