package com.anypoint.logic;

import com.anypoint.PaymentMethod;
import com.anypoint.PriceRequest;
import com.anypoint.PriceResponse;
import com.anypoint.data.Payment;
import com.anypoint.data.PaymentRepository;
import com.anypoint.util.DateTimeFormatter;
import com.anypoint.validation.Validator;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentLogic {

    final static List<PaymentMethod> CARD_PAYMENTS = Arrays.asList(PaymentMethod.AMEX, PaymentMethod.JCB,
            PaymentMethod.MASTERCARD, PaymentMethod.VISA);

    @Autowired
    public PaymentLogic(PriceLogic priceLogic, PointLogic pointLogic, PaymentRepository paymentRepository) {
        this.priceLogic = priceLogic;
        this.pointLogic = pointLogic;
        this.paymentRepository = paymentRepository;
    }

    public PriceLogic priceLogic;

    public PointLogic pointLogic;

    public PaymentRepository paymentRepository;

    @Transactional
    public PriceResponse doPayment(PriceRequest request) {
        Validator.validatePriceRequest(request);

        float finalPrice = priceLogic.calculatePrice(request.getPaymentMethod(),
                request.getPrice(), request.getPriceModifier());
        int points = pointLogic.calculatePoints(request.getPaymentMethod(), request.getPrice());

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        if (request.getPaymentMethod().equals(PaymentMethod.BANK_TRANSFER)) {
            payment.setBankName(request.getAdditionalItem().getBankName());
            payment.setBankAccountNumber(request.getAdditionalItem().getBankAccountNumber());
        } else if (request.getPaymentMethod().equals(PaymentMethod.CHEQUE)) {
            payment.setBankName(request.getAdditionalItem().getBankName());
            payment.setChequeNumber(request.getAdditionalItem().getChequeNumber());
        } else if (CARD_PAYMENTS.contains(request.getPaymentMethod())) {
            payment.setLast4(request.getAdditionalItem().getLast4());
        }
        payment.setAmount(finalPrice);
        payment.setPoints(points);
        payment.setDate(DateTimeFormatter.formatDateTime(request.getDatetime()));
        paymentRepository.save(payment);

        return PriceResponse.newBuilder().setFinalPrice(finalPrice).setPoints(points).build();
    }
}
