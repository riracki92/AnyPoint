package com.anypoint.client;

import com.anypoint.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.
                forAddress("localhost", 6565).
                usePlaintext()
                .build();

        PriceServiceGrpc.PriceServiceBlockingStub blockingStub = PriceServiceGrpc.newBlockingStub(managedChannel);

        PriceRequest priceRequest = PriceRequest.newBuilder()
                .setCustomerId(1)
                .setPrice(1000f)
                .setPriceModifier(1f)
                .setPaymentMethod(PaymentMethod.AMEX)
                .setDatetime("2022-01-05T12:00:00Z")
                .setAdditionalItem(AdditionalItem.newBuilder().setLast4("1234").build())
                .build();

        PriceResponse priceResponse = blockingStub.getPrice(priceRequest);
        log.info("Price Response: " + priceResponse);

        PriceRequest priceRequest2 = PriceRequest.newBuilder()
                .setCustomerId(2)
                .setPrice(2000f)
                .setPriceModifier(1f)
                .setPaymentMethod(PaymentMethod.AMEX)
                .setDatetime("2022-01-05T14:00:00Z")
                .setAdditionalItem(AdditionalItem.newBuilder().setLast4("1234").build())
                .build();

        PriceResponse priceResponse2 = blockingStub.getPrice(priceRequest2);
        log.info("Price Response: " + priceResponse2);

        ReportServiceGrpc.ReportServiceBlockingStub reportBlockingStub = ReportServiceGrpc.newBlockingStub(managedChannel);

        SalesRequest salesRequest = SalesRequest.newBuilder()
                .setStartDateTime("2022-01-01T12:00:00Z")
                .setEndDateTime("2022-01-10T12:00:00Z")
                .build();

        SalesResponse salesResponse = reportBlockingStub.getSales(salesRequest);
        log.info("Sales Response: " + salesResponse);
    }
}
