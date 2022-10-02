package com.anypoint.logic;

import com.anypoint.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class PointLogic {

    public int calculatePoints(PaymentMethod paymentMethod, float price) {
        float points = switch (paymentMethod) {
            case BANK_TRANSFER, CHEQUE, POINTS, UNRECOGNIZED -> 0;
            case GRAB_PAY, LINE_PAY, PAY_PAY -> price * 0.01f;
            case AMEX -> price * 0.02f;
            case MASTERCARD, VISA -> price * 0.03f;
            case CASH, CASH_ON_DELIVERY, JCB -> price * 0.05f;
        };
        return (int) points;
    }
}
