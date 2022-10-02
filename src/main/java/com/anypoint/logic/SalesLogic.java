package com.anypoint.logic;

import com.anypoint.Sales;
import com.anypoint.SalesRequest;
import com.anypoint.SalesResponse;
import com.anypoint.data.Payment;
import com.anypoint.data.PaymentRepository;
import com.anypoint.util.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesLogic {

    public PaymentRepository paymentRepository;

    @Autowired
    public SalesLogic(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public SalesResponse getSales(SalesRequest request) {
        SalesResponse.Builder salesResponseBuilder = SalesResponse.newBuilder();

        List<Payment> payments = paymentRepository.getSales(
                DateTimeFormatter.formatDateTime(request.getStartDateTime()),
                DateTimeFormatter.formatDateTime(request.getEndDateTime()));

        for(Payment payment : payments) {
            Sales sales = Sales.newBuilder()
                    .setDatetime(payment.getDate().toString())
                    .setSales(payment.getAmount())
                    .setPoints(payment.getPoints())
                    .build();
            salesResponseBuilder.addSales(sales);
        }

        return salesResponseBuilder.build();
    }
}
