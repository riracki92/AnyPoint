package com.anypoint.logic;

import com.anypoint.PaymentMethod;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PriceLogic {

    public float calculatePrice(PaymentMethod paymentMethod, float price, float priceModifier) {
        List<PaymentMethod> unmodifiablePayments = Arrays.asList(PaymentMethod.BANK_TRANSFER, PaymentMethod.GRAB_PAY,
                PaymentMethod.LINE_PAY, PaymentMethod.PAY_PAY, PaymentMethod.POINTS);
        if (unmodifiablePayments.contains(paymentMethod)) {
            return price;
        } else {
            if (isPriceModifierValid(paymentMethod, priceModifier)) {
                return price * priceModifier;
            } else {
                throw new IllegalArgumentException("Price modifier is not within range for selected payment method.");
            }
        }
    }

    private boolean isPriceModifierValid(PaymentMethod paymentMethod, float priceModifier) {
        return switch (paymentMethod) {
            case BANK_TRANSFER, GRAB_PAY, LINE_PAY, PAY_PAY, POINTS -> true;
            case CASH, CHEQUE -> priceModifier >= 0.9 && priceModifier <= 1;
            case JCB, MASTERCARD, VISA -> priceModifier >= 0.95 && priceModifier <= 1;
            case AMEX -> priceModifier >= 0.98 && priceModifier <= 1.01;
            case CASH_ON_DELIVERY -> priceModifier >= 1 && priceModifier <= 1.02;
            case UNRECOGNIZED -> false;
        };
    }
}
