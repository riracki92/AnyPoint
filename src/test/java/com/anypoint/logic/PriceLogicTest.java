package com.anypoint.logic;

import com.anypoint.PaymentMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceLogicTest {

    PriceLogic priceLogic = new PriceLogic();

    @Test
    public void shouldReturnPriceWhenPriceModifierIsValid() {
        assertEquals(1000, priceLogic.calculatePrice(PaymentMethod.BANK_TRANSFER, 1000, 0.5f));
        assertEquals(1100, priceLogic.calculatePrice(PaymentMethod.GRAB_PAY, 1100, 0.8f));
        assertEquals(1200, priceLogic.calculatePrice(PaymentMethod.LINE_PAY, 1200, 1f));
        assertEquals(1300, priceLogic.calculatePrice(PaymentMethod.PAY_PAY, 1300, 1.2f));
        assertEquals(1400, priceLogic.calculatePrice(PaymentMethod.POINTS, 1400, 1.5f));

        assertEquals(1500, priceLogic.calculatePrice(PaymentMethod.CASH, 1500, 1f));
        assertEquals(1600, priceLogic.calculatePrice(PaymentMethod.CHEQUE, 1600, 1f));

        assertEquals(1700, priceLogic.calculatePrice(PaymentMethod.JCB, 1700, 1f));
        assertEquals(1800, priceLogic.calculatePrice(PaymentMethod.MASTERCARD, 1800, 1f));
        assertEquals(1900, priceLogic.calculatePrice(PaymentMethod.VISA, 1900, 1f));

        assertEquals(2000, priceLogic.calculatePrice(PaymentMethod.AMEX, 2000, 1f));

        assertEquals(2100, priceLogic.calculatePrice(PaymentMethod.CASH_ON_DELIVERY, 2100, 1f));
    }

    @Test
    public void shouldThrowExceptionWhenPriceModifierIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.CASH, 1000, 0.8f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.CASH, 1000, 1.1f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.CHEQUE, 1000, 0.8f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.CHEQUE, 1000, 1.1f));

        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.JCB, 1000, 0.9f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.JCB, 1000, 1.1f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.MASTERCARD, 1000, 0.9f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.MASTERCARD, 1000, 1.1f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.VISA, 1000, 0.9f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.VISA, 1000, 1.1f));

        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.AMEX, 1000f, 0.95f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.AMEX, 1000f, 1.1f));

        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.CASH_ON_DELIVERY, 1000f, 0.9f));
        assertThrows(IllegalArgumentException.class,
                () -> priceLogic.calculatePrice(PaymentMethod.CASH_ON_DELIVERY, 1000f, 1.05f));
    }
}
