package com.anypoint.logic;

import com.anypoint.PaymentMethod;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointLogicTest {

    PointLogic pointLogic = new PointLogic();

    @Test
    public void calculatePointsTest() {
        assertEquals(0, pointLogic.calculatePoints(PaymentMethod.BANK_TRANSFER, 1000f));
        assertEquals(0, pointLogic.calculatePoints(PaymentMethod.CHEQUE, 1000f));
        assertEquals(0, pointLogic.calculatePoints(PaymentMethod.POINTS, 1000f));
        assertEquals(10, pointLogic.calculatePoints(PaymentMethod.GRAB_PAY, 1000f));
        assertEquals(10, pointLogic.calculatePoints(PaymentMethod.LINE_PAY, 1000f));
        assertEquals(10, pointLogic.calculatePoints(PaymentMethod.PAY_PAY, 1000f));
        assertEquals(20, pointLogic.calculatePoints(PaymentMethod.AMEX, 1000f));
        assertEquals(30, pointLogic.calculatePoints(PaymentMethod.MASTERCARD, 1000f));
        assertEquals(30, pointLogic.calculatePoints(PaymentMethod.VISA, 1000f));
        assertEquals(50, pointLogic.calculatePoints(PaymentMethod.CASH, 1000f));
        assertEquals(50, pointLogic.calculatePoints(PaymentMethod.CASH_ON_DELIVERY, 1000f));
        assertEquals(50, pointLogic.calculatePoints(PaymentMethod.JCB, 1000f));
    }
}
